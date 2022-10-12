# .NET commands

|Command|Explanation|
|---|---|
|`dotnet new Console <name>`|Creates a new .NET project in a subfolder called `<name>`|
|`dotnet run <name>`|Runs the project called `<name>`, or if `<name>` is not specified, it will run the project in the current folder (with a .csproj file)|
|`dotnet build <name>`|Builds the project with the specified `<name>`|
|`dotnet add package <package>`|Adds the package with the name `<package>` which can then be used in the project using `using <package>`|
|`dotnet test`|Runs all XUnit tests in the current directory and all subdirectories/projects|
|`dotnet test /p:CollectCoverage=true`|Test the project in the active directory and subfolders and measure the test code coverage.<br> Prerequisite: <br>`dotnet add package coverlet.msbuild`|
