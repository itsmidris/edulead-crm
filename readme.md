# EduLead CRM

A modern **Student Admission & Lead Management CRM** built using **Spring Boot** and **Thymeleaf** for educational institutes to efficiently manage student inquiries, follow-ups, counseling sessions, admissions, and user management.

---

## 🚀 Technical Highlights

### Backend
- Java 25
- Spring Boot 4
- Spring MVC
- Spring Data JPA (Hibernate)
- Spring Security
- RESTful APIs
- Bean Validation
- DTO Pattern
- Entity-DTO Mapper
- Global Exception Handling
- Layered Architecture
- Feature-Based Modular Architecture
- BCrypt Password Encryption

### Frontend
- Thymeleaf
- Bootstrap 5
- HTML5
- CSS3
- JavaScript
- Responsive Design

### Database
- PostgreSQL
- JPA/Hibernate ORM
- Entity Relationships
- Database Constraints
- Auditing (Created & Updated Timestamps)

### Development Tools
- Maven
- Git
- GitHub
- IntelliJ IDEA
- Postman

---

# 🎯 Skills Demonstrated

- Object-Oriented Programming (OOP)
- MVC Architecture
- Dependency Injection (IoC)
- Authentication & Authorization
- CRUD Operations
- DTO Mapping
- Repository Pattern
- Service Layer Pattern
- Form Validation
- Exception Handling
- Feature-Based Modular Architecture
- Clean Code Principles
- Responsive UI Development
- Database Design & Entity Relationships
- Version Control using Git

---

# 📖 Project Overview

EduLead CRM is a web-based Customer Relationship Management system designed for educational institutes and coaching centers.

The application helps manage the complete admission workflow, from receiving student inquiries to counseling and admission.

Users can:

- Manage student leads
- Manage available courses
- Schedule counseling sessions
- Track call history
- Manage application users
- Monitor lead progress
- Maintain admission records

---

# ✨ Features

## Dashboard
- Dashboard overview
- Quick statistics
- Recent activities
- Analytics ready

## User Management
- User Registration
- Login
- Change Password
- Update User
- Role-Based Access

## Lead Management
- Add Lead
- Edit Lead
- Delete Lead
- Search Leads
- Lead Status Tracking
- Follow-up Management

## Course Management
- Create Course
- Update Course
- Activate/Deactivate Course
- Course Listing

## Counseling Management
- Schedule Counseling
- Assign Counselor
- Counseling Mode
- Counseling Status
- Counseling Outcome
- Counseling Notes

## Call Log Management
- Record Call History
- Call Duration
- Call Notes
- Follow-up Tracking

---

# 🏗 Project Architecture

The project follows a **Feature-Based Modular Architecture**, where every feature contains its own:

- Controller
- ViewController
- Service
- Service Implementation
- Repository
- DTO
- Mapper
- Entity

```
src
└── main
    ├── java
    │   └── com.itsmidris.edulead_crm
    │       ├── auth
    │       ├── calllog
    │       ├── common
    │       ├── config
    │       ├── counseling
    │       ├── course
    │       ├── dashboard
    │       ├── followup
    │       ├── lead
    │       ├── security
    │       └── user
    │
    └── resources
        ├── templates
        ├── static
        └── application.properties
```

---

# 🛠 Technology Stack

| Category | Technology |
|----------|------------|
| Language | Java 25 |
| Framework | Spring Boot 4 |
| Security | Spring Security |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL |
| Frontend | Thymeleaf |
| Styling | Bootstrap 5 |
| Build Tool | Maven |
| Version Control | Git & GitHub |
| API Testing | Postman |

---

# 🔐 Security

- Spring Security Authentication
- Password Encryption using BCrypt
- Role-Based Access Control
- Secure Login

---

# ⚙️ Installation

## Clone Repository

```bash
git clone https://github.com/itsmidris/edulead-crm.git
```

## Navigate

```bash
cd edulead-crm
```

## Configure Database

Update the database configuration inside:

```
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/edulead_crm
spring.datasource.username=root
spring.datasource.password=yourpassword
```

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

Open:

```
http://localhost:8080
```

---

# 📸 Application Screenshots

## 🔐 Login

![Login](docs/Login.png)

## 🏠 Dashboard

![Dashboard](docs/Dashboard.png)

## 👥 Lead Management

![Lead List](docs/Lead-List.png)

![Add Lead](docs/Add-Lead-1.png)

![Add Lead](docs/Add-Lead-2.png)

## 📚 Course Management

![Course List](docs/Course-List.png)

![Create Course](docs/Create-Course.png)

## 📅 Follow-up Management

![Follow-up List](docs/FollowUp-List.png)

![Create Follow-up](docs/Create-Followup.png)

## 📞 Call Log Management

![Call Logs](docs/Call-Logs.png)
![All Call Logs](docs/All-Calls.png)
![Caller History](docs/Caller-History.png)
![Caller History Result](docs/Caller-History-Result.png)
![Lead History](docs/Lead-History.png)
![Lead History](docs/Lead-History-Result.png)
![Filter By Outcome](docs/Filter-By-Outcome.png)
![Filter By Outcome](docs/Filter-By-Outcome-Result.png)
![Fiter By Date Range](docs/Filter-By-Date-Range.png)
![Fiter By Date Range](docs/Filter-By-Date-Range-Result.png)

---

# 🗺 Roadmap

- ✅ User Module
- ✅ Course Module
- ✅ Lead Module
- ✅ Counseling Module
- ✅ Call Log Module
- ✅ Authentication
- ✅ Thymeleaf UI
- ✅ Dashboard Analytics
- ⏳ Pagination & Sorting
- ⏳ CSV Lead Import
- ⏳ Email Notifications
- ⏳ Reports
- ⏳ Docker Support
- ⏳ Cloud Deployment

---

# 🤝 Contributing

Contributions, suggestions, and improvements are welcome.

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

---

# 👨‍💻 Author

**Md Imran Idrishi**

1. GitHub: https://github.com/itsmidris
2. LinkedIn: https://linkedin.com/in/itsmidris

---

# ⭐ Show Your Support

If you found this project helpful, please consider giving it a ⭐ on GitHub.

Your support motivates further development and improvements.