# Todo App CI/CD

Console Todo application in Java 17. It demonstrates GitHub Flow, Gradle builds, JUnit 5 tests, GitHub Actions, Releases, and GitHub Packages.

Repository: https://github.com/Semka-prog/todo-app-ci-cd

## Requirements

- JDK 17 or newer
- Git
- Internet access for Gradle dependencies

## Build and test

```powershell
.\gradlew.bat clean build
```

The executable fat-JAR is generated at:

```text
build/libs/todo-app.jar
```

Run it with:

```powershell
java -jar build/libs/todo-app.jar
```

## Commands

- `add <text>` - add a task
- `list` - show tasks
- `done <index>` - mark a task as completed
- `clear` - remove all tasks
- `search <text>` - find tasks by title
- `help` - show command help
- `exit` - quit

## CI/CD

- [CI workflow](.github/workflows/ci.yml) runs tests for pushes and pull requests to `main`.
- [Release workflow](.github/workflows/release.yml) runs for tags such as `v1.0.0`, tests, publishes a GitHub Release with `todo-app.jar`, and publishes the Maven package to GitHub Packages.
- [Nightly workflow](.github/workflows/nightly.yml) runs at 03:00 UTC and does not create a release or package.

## Release process

```powershell
git tag v1.0.0
git push origin v1.0.0
```

Package coordinates:

```text
org.example:todo-app:<version>
```
