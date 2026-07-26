# Coffee Menu Service

REST API สำหรับจัดการเมนูกาแฟ (In-memory, ยังไม่ใช้ฐานข้อมูล)
พัฒนาด้วย Spring Boot ตาม Lab05 (CP353002 - Principles of Software Design and Development)

## ความต้องการของระบบ
- Java 17
- Spring Boot 3.x
- Spring Web
- Maven

## โครงสร้างโปรเจกต์
- `Coffee.java` — Model เก็บข้อมูลกาแฟ (id, name, price)
- `CoffeeService.java` — Service เก็บ logic และข้อมูลใน `List<Coffee>`
- `CoffeeController.java` — Controller รับ HTTP request แล้วส่งต่อให้ Service

## วิธีรัน

1. รันแอปพลิเคชันด้วย Maven wrapper
```bash
./mvnw spring-boot:run
```

2. แอปจะรันที่ `http://localhost:8080`
   ข้อมูลตั้งต้น (preload) จะมี Espresso (id=1) และ Latte (id=2) พร้อมทดสอบทันที

## ตัวอย่างการเรียก API

> ทดสอบด้วย PowerShell (`Invoke-RestMethod`)

### ดูรายการกาแฟทั้งหมด
```powershell
Invoke-RestMethod -Uri http://localhost:8080/coffees -Method Get | ConvertTo-Json
```

### ดูกาแฟตาม id
```powershell
Invoke-RestMethod -Uri http://localhost:8080/coffees/1 -Method Get | ConvertTo-Json
```

### เพิ่มกาแฟใหม่
```powershell
Invoke-RestMethod -Uri http://localhost:8080/coffees -Method Post -ContentType "application/json" -Body '{"name":"Mocha","price":60}' | ConvertTo-Json
```

### แก้ไขกาแฟ
```powershell
Invoke-RestMethod -Uri http://localhost:8080/coffees/1 -Method Put -ContentType "application/json" -Body '{"name":"Espresso Double","price":50}' | ConvertTo-Json
```

### ลบกาแฟ
```powershell
Invoke-RestMethod -Uri http://localhost:8080/coffees/1 -Method Delete
```

## Endpoints

| Method | Path | คำอธิบาย |
|--------|------|----------|
| GET | `/coffees` | ดูรายการกาแฟทั้งหมด |
| GET | `/coffees/{id}` | ดูกาแฟตาม id |
| POST | `/coffees` | เพิ่มกาแฟใหม่ |
| PUT | `/coffees/{id}` | แก้ไขกาแฟ |
| DELETE | `/coffees/{id}` | ลบกาแฟ |
