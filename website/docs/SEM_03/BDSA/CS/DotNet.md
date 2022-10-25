# .NET commands

|Command|Explanation|
|---|---|
|`dotnet new Console <name>`|Creates a new .NET project in a subfolder called `<name>`|
|`dotnet run <name>`|Runs the project called `<name>`, or if `<name>` is not specified, it will run the project in the current folder (with a .csproj file)|
|`dotnet build <name>`|Builds the project with the specified `<name>`|
|`dotnet add package <package>`|Adds the package with the name `<package>` which can then be used in the project using `using <package>`|
|`dotnet test`|Runs all XUnit tests in the current directory and all subdirectories/projects|
|`dotnet test /p:CollectCoverage=true`|Test the project in the active directory and subfolders and measure the test code coverage.<br></br> Prerequisite: <br></br>`dotnet add package coverlet.msbuild`|

## Publishing software

When running code locally, `dotnet run` can be used, which calls `dotnet build`. However, end-users are not expected to use terminal commands to run programs.

`dotnet build` gives an exe file that can only be used by users on the same environment.

`dotnet publish` can be used for cross-platform software.

Add `-p:AssemblyName=<name>` to give the output file a different name.

### Static linking

Libraries needed to run the software is given with the software in the required versions etc.

Artifacts such as `.txt` or `.jpg` files etc. are not part of the built software. `Go`-lang supports this by something like:

```go
import _ "embed"

//go:embed hello.txt
var s string
print(s)
```

### Dynamic linking

Libraries needed to run the software is used across different software programs.
