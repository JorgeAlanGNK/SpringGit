# Git Repositorio aplicativo Java
Los desarrolladores hoy en día estan dependiendo de Github, por lo que, estas aplicaciones de escritorio ayudan bastante en realizar nuevos procedimientos y existen variedades como son estos.

> ## Lista de Aplicaciones Git
>- SourceThree
>- GitHub Desktop
>- GitKraken
>- QGit...

Existen una gran variedad de aplicaciones de Git para tener un conjunto de repositorios, pero no para tener un conjunto de usuarios que puedan visualizar.

Ejemplo: Tengo un proyecto de avance con más avances, pero necesito trabajar en otro proyecto y así sucesivamente, actualmente git necesita modificadores de acceso a sus plataformas mediante claves de seguridad para el repositorio (__usuario root__), pero quiero tener más colaboradores y aunque sean nuevos debo guardar los tokens actualmente para tener un orden.

## Conectividad SSH
> La conectividad de SSH permite tener una clave de acceso digital a través de una máquina o servidor, pero github en su implementación espera este clave de acceso, muchos lo realizan por medios de web u otras paginas, hasta incluso esperar el lider de proyecto o permisos especiales que me permitan cargar esta credencial.<br>
> Sin embargo, esta conectividad funcionan con procesos sencillos para el repositorio, ya que, se necesita un generador de tokens, sin esperar procesos, y que la máquina de un servidor pueda generar esta clave de forma pública dentro de la consola.<br>
> 

## Tokens Path
> Los tokens PATHS son para claves de acceso de seguridad, compartiendo las credenciales de acceso a los usuarios, tienen ventajas como trabajar de manera remota, visualizar cambios, y visualizar el nombre del usuario que esta trabajando. Por lo general git no proporciona un almacenamiento para estos tokens de conectividad que genera constantemente a nuestro gusto como __desarrollador__, sin embargo, no tengo un nombre de usuario.<br>
> El segundo método PATH es incrustar esta clave en la URL del repositorio de trabajo, es más sencillo de manejar pero inseguro para la identificación, ya que, un usuario puede visualizar el token al realizar un comando de git.<br>
> `git remote -v`.<br>

Por lo general estos nombres de usuarios tienen por url de empresa, pero para personas que no tengan una empresa y están intentando acceder a diferentes repositorios, deben de pasar este token hacia otro usuario y estas aplicaciones se instalan de manera manual, pero debo de depender de otras, es decir, Github Desktop dependo para subir o realizar cambios para mi aplicativo. Pero necesito identificarme, ya que, Github obligo a todo el mundo tener una Autentificación de usuario para acceder al repo y realizar operaciones más básicas como.
- Pull Request
- Clone
- Push

> # NOTA
> Es importante recordar que este aplicativo sigue en desarrollo, en donde sigue en desarrollo, precaución, este repositorio esta bajo licencia MIT, favor de comunicarse con el creador actual