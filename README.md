# telegram-reactive-stats-bot

Internship project

## Deploy

This project deploy in Google Cloud App Engine. Follow next steps to get opportunity to auto deploy:

1) Download the Google Cloud SDK from
   [here](https://cloud.google.com/sdk/docs/install)
2) Log in and chose correct project name

To deploy all modules write:

```
./deploy
```

To deploy single module:

```
cd <module_name> && mvn clean package appengine:deploy
```

You can stream logs from the command line by running:

```
gcloud app logs tail -s <module_name>
```

To view your application in the web browser run:

```
gcloud app browse -s <module_name>
```

To stop all instance with equal version:

```
gcloud app versions stop <number_of_version>
```