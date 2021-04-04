# telegram-reactive-stats-bot
Internship project

## Deploy
This project deploy in AWS ES. Follow next steps to get opportunity to auto deploy:

1) Download the Elastic Beanstalk CLI tool from
[here](https://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb-cli3-install.html)
2) You need get info about your "Access Key ID" and "Secret Access Key" read
[this](https://aws.amazon.com/blogs/security/wheres-my-secret-access-key/#:~:text=Secret%20access%20keys%20are%E2%80%94as,key%20after%20its%20initial%20creation.)

To deploy all modules write: 

```
./deploy
```

To deploy single module:

```
cd <module_name> && eb deploy
```

To see deployment info write this at one of module directory:

```
eb console 
```