# About
Implementation of [markdown-taking-app](https://roadmap.sh/projects/markdown-note-taking-app) project.

The idea is to use `rust` for most of the project without leveraging any external library.
This means implementing the AST for the markdown and html serializer and creating the HTTP server 
manually. As for the grammar checking, it may use an external service.

## Tasks Assumptions
This is a training project so we will scope some elements as we move forward.

* Mardown parser
    * We assume the input is a valid UTF-8 sequence of characters. Different encodings
    may pose an issue during the tokenization.
