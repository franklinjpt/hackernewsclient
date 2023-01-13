# Hacker News Client

This is a template repository for the [Hacker News Client](https://courses.fit.cvut.cz/BI-OOP/projects/hackernews-cli.html) project.


The full project assignment can be found on the official [courses page](https://courses.fit.cvut.cz/BI-OOP/projects/hackernews-cli.html).

## Project Structure

```
.idea/          - Idea specific files
project/        - SBT configuration files
src/
├── main/
│   └── scala/  - Scala source files
└── test/
    └── scala/  - Scala tests
target/
.gitlab-ci.yml  - CI pipeline configuration
.scalafmt.conf  - scalafmt formatting rules
build.sbt       - SBT build file
hnc             - executable script
README.md      
```

## Build

The project can be build either using IntelliJ Idea directly or by using the following `sbt` command:

```
sbt assembly
```

This command compiles the entire code, runs the tests and produces a jar including all dependencies.

## Usage

Same as building, the project can be run through the IntelliJ Idea; by running the sbt command:

```
sbt "run top page=2"
```

```
sbt "run help"
```

or, after building with the `sbt assembly`, by running the prepared executable script:

```
./hnc top-stories --page=2
```
