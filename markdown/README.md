# About

Implementation for this project: <https://roadmap.sh/projects/markdown-note-taking-app>

## Implementation details

The idea is using two services.

First, an API that will be used for upload and fetch the processed markdown html.
Second, a job that will take the events from a kakfa topics and process the stored markdown.