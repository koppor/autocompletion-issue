# Demonstration of IntelliJ issue

This is to demonstracte an autocompletion issue at IntelliJ 2020.1.1 201.7223.91

When having a class with >50 members, the auto completion does not start the search *within* the available fields.

Filed as [IDEA-240542](https://youtrack.jetbrains.com/issue/IDEA-240542).

## Video demonstrating the issue

Following things are done in the video:

1. Normal auto completion by selecting an entry in the proposals.
2. Triggering auto completion and then pressing the letter <kbd>e</kbd> --> no search triggered.
3. Triggering auto completion at `String` and then pressing the letter <kbd>e</kbd> --> search at methods triggered.

<a href="https://www.loom.com/share/2690137cbd03452e8bc3d0368cc055db">
    <img style="max-width:300px;" src="https://cdn.loom.com/sessions/thumbnails/2690137cbd03452e8bc3d0368cc055db-with-play.gif">
</a>

<!-- markdownlint-disable-file MD033 -->
