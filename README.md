# bookings-synchronizer

It will check every 10 minutes all Ogado confirmed booking having the check-in-date in the future, and compare with the supplier the details of the booking.If there is any change of dates or status, update accordingly Ogado booking.

# Code execution instructions

From project directory,

## build
```
mvn clean install
```

## run
```
mvn exec:java
```