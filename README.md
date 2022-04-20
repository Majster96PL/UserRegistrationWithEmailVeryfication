# User Registration with email verification backend
**Backend register form using email token verification.**

## Technology
**Java,
Spring Web,
Spring Security,
Spring Data JPA,
MySQL,
Lombock.**

## Installation
- Download the project,
- Create DataBase,
- Change 'ddl-auto' in aplication.properties from  'update' to 'create',
- Run project,
- Using Postmam to testing.

## Data Base MySQL
```mysql
CREATE DATABASE registrationuser;
```

## Testing 
- First You have to write in 'body' json text and then send inquiry method HTTP POST.
```
http://localhost:8080/registration
```
```
{
    "firstName": "test",
    "lastName": "test",
    "email": "test@gmail.com",
    "password": "test"
}
```
![image](https://user-images.githubusercontent.com/80486633/164289193-4c696588-ba9e-4b3f-a444-3fe2219466ab.png)
- Copy the token that generated and then send inquiry method HTTP GET to verify email.
```
http://localhost:8080/registration/confirm?token=your_token
```
![image](https://user-images.githubusercontent.com/80486633/164290236-1803ce54-90df-4ac9-8bf1-7979d32af2aa.png)


I'm using Postman to test and mailtrap.io to confirm email.

![image](https://user-images.githubusercontent.com/80486633/164292588-4793afd7-2a44-4515-b795-f05fd366cad0.png)
