### How to build & run your server app and SPA on a local machine
1. Server app
- cd to `bookstore-be` folder
- Build server with gradle
- run application with port 8080
- Opem api URL: http://localhost:8080/v3/api-docs/
2. SPA
- install nodejs
- cd to `bookstore-fe` folder
- Run comand line: `npm install` to import library
- Run server with command: `ng serve --open` to run server
- Open URL: http://localhost:4200/ 

### Reasons your application of design / patterns, i.e.
- Easy to build/develop
- Using OpenAPI to easy generate to FE help save time to code
- Create services to help reduce dependencies injection. This makes it easier for others to keep building code by adding more functionality to a new class, using yours as a parent. It is the idea that no clients should be forced to depend on properties or methods that they don’t use or need.
- In Frontend: I divide them into small component for easy development and will be less likely to impact with other functions.

### Time summary:
- Preparation: 30 minute
- Coding: 10 hours (including tests)
- Styling 1 hour
- Building and testing 30 minutes

**Grand total: 12 hours
