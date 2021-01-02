FROM openjdk:11
ADD target/bookings-synchronizer-0.0.1-SNAPSHOT bookings-synchronizer-0.0.1-SNAPSHOT
ENTRYPOINT ["java", "-jar", "bookings-synchronizer-0.0.1-SNAPSHOT", "com.ogado.synchronizer.SynchronizationApplication"]