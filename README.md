# Masterstroke Android

The Android client for Masterstroke Technoservices. This repository currently contains only the technical and UI foundation; attendance, billing, payroll, OCR, invoices, and final authentication rules are intentionally out of scope until the source documents and backend contracts are approved.

## Structure

- `data/`: temporary preview implementations and future remote/local data sources.
- `domain/`: stable application contracts and small models.
- `presentation/`: Compose screens, app navigation, theme, and ViewModels.

The preview session is deliberately in-memory and contains no credentials. Future secure authentication, Room-backed offline actions, and Retrofit/OkHttp API implementations should sit behind repository interfaces. Business calculations remain backend-owned.

## Build

Use JDK 17 and an Android SDK with API 35 installed:

```sh
./gradlew :app:assembleDebug
```

`local.properties` is intentionally ignored because it contains the machine-specific Android SDK path.
