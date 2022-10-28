# Swagger

```sh
dotnet add package Swashbuckle.AspNetCore
```

```cs
builder.Services.AddSwaggerGen();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}
```
