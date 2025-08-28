use axum::Json;
use axum::Router;
use axum::extract::Request;
use axum::middleware::Next;
use axum::middleware::from_fn;
use axum::response::Response;
use axum::routing::get;
use axum::serve;
use serde_json::Value;
use serde_json::json;
use tokio::net::TcpListener;
use tower::ServiceBuilder;
use tracing::Level;
use tracing::event;
use tracing::span;
use tracing_subscriber::FmtSubscriber;

#[tokio::main]
async fn main() {
    let subscriber = FmtSubscriber::builder()
        .with_max_level(Level::TRACE)
        .finish();

    tracing::subscriber::set_global_default(subscriber).expect("setting default subscriber failed");

    let app = Router::new().route("/", get(hello_world)).layer(
        ServiceBuilder::new()
            .layer(from_fn(log_request_layer))
            .layer(from_fn(layer_02)),
    );

    let listener = TcpListener::bind("0.0.0.0:3000").await.unwrap();
    serve(listener, app).await.unwrap();
}

async fn hello_world() -> Json<Value> {
    Json(json!({
        "message": "hello world!"
    }))
}

async fn log_request_layer(request: Request, next: Next) -> Response {
    let span = span!(Level::TRACE, "request");
    let _enter = span.enter();

    event!(Level::INFO, "request arrived {:?}", request);
    let response = next.run(request).await;

    return response;
}

async fn layer_02(request: Request, next: Next) -> Response {
    event!(Level::INFO, "Layer 2 has been called");
    let response = next.run(request).await;
    return response;
}
