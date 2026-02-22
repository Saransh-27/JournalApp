# 📘 JournalApp

A comprehensive, secure, and feature-rich journal application built with Spring Boot that provides end-to-end encryption (E2EE) for personal journal entries with advanced features including sentiment analysis, weather integration, and email notifications.

## 🌟 Features

### 🔐 Authentication & Security
- **JWT-based Authentication**: Secure token-based authentication system
- **Google OAuth2 Integration**: Sign in with Google accounts
- **Role-based Access Control**: USER and ADMIN roles with different permissions
- **Password Encryption**: BCrypt password hashing
- **Spring Security**: Comprehensive security configuration

### 📝 Journal Management
- **Complete CRUD Operations**: Create, Read, Update, Delete journal entries
- **Sentiment Analysis**: Automatic sentiment detection (HAPPY, SAD, ANGRY, ANXIOUS)
- **Date-based Organization**: Entries organized by timestamp
- **User-specific Entries**: Each user has their own private journal collection

### 🌦️ External Integrations
- **Weather Service**: Real-time weather information integration
- **Email Notifications**: Automated email reports and notifications
- **Google OAuth2**: Social login functionality

### ⚡ Performance & Caching
- **Redis Caching**: High-performance caching for weather data and frequently accessed data
- **Scheduled Tasks**: Automated sentiment analysis reports via email
- **RestTemplate**: HTTP client for external API calls

### 📚 API Documentation
- **Swagger/OpenAPI 3**: Interactive API documentation
- **JWT Authentication Support**: Bearer token authentication in Swagger UI
- **Comprehensive Endpoints**: Well-documented REST APIs

## 🛠️ Tech Stack

### Core Technologies
| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 17 | Programming Language |
| **Spring Boot** | 3.5.6 | Backend Framework |
| **Maven** | - | Build & Dependency Management |
| **Spring Security** | 6.x | Authentication & Authorization |
| **Spring Data MongoDB** | 3.x | Database Integration |

### Database & Caching
| Technology | Purpose |
|------------|---------|
| **MongoDB** | Primary Database (NoSQL) |
| **Redis** | Caching Layer |
| **Spring Data MongoDB** | MongoDB Repository Pattern |

### Security & Authentication
| Technology | Version | Purpose |
|------------|---------|---------|
| **JWT (JJWT)** | 0.12.5 | Token Generation & Validation |
| **Spring Security** | 6.x | Security Framework |
| **BCrypt** | - | Password Hashing |
| **Google OAuth2** | - | Social Authentication |

### External APIs & Services
| Service | Purpose |
|---------|---------|
| **Weather API** | Real-time weather data |
| **Google OAuth2** | User authentication |
| **Spring Mail** | Email notifications |

### Documentation & Testing
| Technology | Version | Purpose |
|------------|---------|---------|
| **Swagger/OpenAPI** | 2.8.15 | API Documentation |
| **JUnit 5** | - | Unit Testing |
| **Mockito** | 5.10.0 | Mock Testing |
| **JUnit Jupiter Params** | - | Parameterized Tests |

### Development Tools
| Technology | Version | Purpose |
|------------|---------|---------|
| **Spring Boot DevTools** | - | Hot Reload |
| **Lombok** | 1.18.42 | Code Generation |
| **Spring Configuration Processor** | - | Metadata Generation |

## 📁 Project Structure

```
src/main/java/com/company/journalApp/
├── 📁 api/
│   └── 📁 response/
│       └── WeatherResponse.java
├── 📁 cache/
│   └── AppCache.java
├── 📁 config/
│   ├── RedisConfig.java
│   ├── SpringSecurity.java
│   └── SwaggerConfig.java
├── 📁 controller/
│   ├── AdminController.java
│   ├── GoogleAuthController.java
│   ├── JournalEntryController.java
│   ├── PublicController.java
│   └── UserController.java
├── 📁 dto/
│   ├── LoginDTO.java
│   ├── SignupDTO.java
│   ├── UserDTO.java
│   └── WeatherDTO.java
├── 📁 entity/
│   ├── ConfigJournalAppEntity.java
│   ├── JournalEntry.java
│   └── User.java
├── 📁 enums/
│   └── Sentiment.java
├── 📁 filter/
│   └── JwtFilter.java
├── 📁 mapper/
│   ├── JournalEntryMapper.java
│   └── UserMapper.java
├── 📁 repositry/
│   ├── JournalRepo.java
│   ├── UserRepo.java
│   └── UserRepoimpl.java
├── 📁 Schedulers/
│   └── UserSchedulers.java
├── 📁 service/
│   ├── EmailService.java
│   ├── GoogleAuthService.java
│   ├── JournalEntryService.java
│   ├── RedisService.java
│   ├── UserDetailsServiceImpl.java
│   ├── UserService.java
│   └── WeatherService.java
├── 📁 utilis/
│   └── JwtUtil.java
└── JournalAppApplication.java
```

## 🚀 Getting Started

### Prerequisites
- **Java 17** or higher
- **Maven 3.6+**
- **MongoDB** (local or cloud instance)
- **Redis** (local or cloud instance)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Saransh-27/JournalApp.git
   cd JournalApp
   ```

2. **Configure Environment Variables**
   Create `application.yml` in `src/main/resources/`:
   ```yaml
   spring:
     data:
       mongodb:
         uri: mongodb://localhost:27017/journalApp
     redis:
       host: localhost
       port: 6379
     mail:
       host: smtp.gmail.com
       port: 587
       username: your-email@gmail.com
       password: your-app-password
   
   weather:
     api:
       key: your-weather-api-key
   
   spring:
     security:
       oauth2:
         client:
           registration:
             google:
               client-id: your-google-client-id
               client-secret: your-google-client-secret
   ```

3. **Build and Run**
   ```bash
   # Using Maven Wrapper
   ./mvnw clean install
   ./mvnw spring-boot:run
   
   # Or using Maven
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access the Application**
   - **Application**: http://localhost:8080
   - **Swagger UI**: http://localhost:8080/swagger-ui.html
   - **API Docs**: http://localhost:8080/v3/api-docs

## 📚 API Endpoints

### Public Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/public/health-check` | Health check endpoint |
| POST | `/public/signup` | User registration |
| POST | `/public/login` | User login (returns JWT) |
| POST | `/public/google-auth` | Google OAuth2 authentication |

### Journal Endpoints (Authenticated)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/journal` | Get all journal entries |
| POST | `/journal` | Create new journal entry |
| GET | `/journal/{id}` | Get specific journal entry |
| PUT | `/journal/{id}` | Update journal entry |
| DELETE | `/journal/{id}` | Delete journal entry |

### User Endpoints (Authenticated)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/user` | Get user profile |
| PUT | `/user` | Update user profile |
| DELETE | `/user` | Delete user account |

### Admin Endpoints (Admin Role)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/admin/users` | Get all users |
| GET | `/admin/journals` | Get all journal entries |

## 🔐 Authentication

### JWT Token Usage
Include the JWT token in the Authorization header:
```bash
Authorization: Bearer <your-jwt-token>
```

### Google OAuth2 Flow
1. Get authorization code from Google
2. Exchange code for access token
3. Get user information from Google
4. Create/update user in database
5. Generate JWT token for the user

## 📦 Dependencies

### Core Spring Boot Starters
- `spring-boot-starter-web`: Web MVC framework
- `spring-boot-starter-security`: Security framework
- `spring-boot-starter-data-mongodb`: MongoDB integration
- `spring-boot-starter-data-redis`: Redis integration
- `spring-boot-starter-mail`: Email functionality
- `spring-boot-devtools`: Development tools

### JWT & Security
- `jjwt-api`, `jjwt-jackson`, `jjwt-impl`: JWT token handling

### Documentation
- `springdoc-openapi-starter-webmvc-ui`: Swagger/OpenAPI documentation

### Development & Testing
- `lombok`: Code generation
- `spring-boot-configuration-processor`: Configuration metadata
- `spring-boot-starter-test`: Testing framework
- `mockito-core`: Mock testing
- `junit-jupiter-api`, `junit-jupiter-params`: Unit testing

## 🔄 Scheduled Tasks

### Sentiment Analysis Report
- **Schedule**: Daily at midnight (0 0 0 * * ?)
- **Purpose**: Analyze user journal sentiments from the last 7 days
- **Action**: Send email reports to users with sentiment analysis

## 🌐 External API Integrations

### Weather API
- **Provider**: WeatherAPI.com (or similar)
- **Caching**: Redis with 5-minute TTL
- **Usage**: Enhance journal entries with weather context

### Google OAuth2
- **Endpoints**: 
  - Token Exchange: `https://oauth2.googleapis.com/token`
  - User Info: `https://oauth2.googleapis.com/tokeninfo`
- **Redirect URI**: `https://developers.google.com/oauthplayground`

## 🧪 Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=JournalEntryServiceTest

# Run with coverage
mvn clean test jacoco:report
```

### Test Configuration
- Tests are currently skipped in Maven configuration (`<skipTests>true</skipTests>`)
- Enable testing by setting `<skipTests>false</skipTests>` in `pom.xml`

## 🔧 Configuration

### Application Properties
Key configuration properties:
- `spring.data.mongodb.uri`: MongoDB connection string
- `spring.redis.host/port`: Redis connection settings
- `spring.mail.*`: Email configuration
- `weather.api.key`: Weather API key
- `spring.security.oauth2.client.*`: Google OAuth2 settings

### Security Configuration
- JWT token expiration: Configurable
- Password encoding: BCrypt with default strength
- CORS: Configured for development environment

## 🚀 Deployment

### Docker Deployment (Optional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/journalApp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Environment Variables for Production
- `MONGODB_URI`: MongoDB connection string
- `REDIS_HOST`: Redis host
- `REDIS_PORT`: Redis port
- `JWT_SECRET`: JWT signing secret
- `WEATHER_API_KEY`: Weather API key
- `GOOGLE_CLIENT_ID`: Google OAuth2 client ID
- `GOOGLE_CLIENT_SECRET`: Google OAuth2 client secret

## 📊 Monitoring & Logging

### Logging
- Framework: SLF4J with Logback
- Log levels: Configurable in `application.yml`
- Structured logging for better observability

### Health Checks
- Endpoint: `/public/health-check`
- Returns: "OK" if application is running

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines
- Follow Java coding conventions
- Write meaningful commit messages
- Add tests for new features
- Update documentation for API changes

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the GitHub repository
- Check the API documentation at `/swagger-ui.html`
- Review the application logs for troubleshooting

## 🔄 Version History

- **v0.0.1-SNAPSHOT**: Initial release with core functionality
  - Basic CRUD operations for journal entries
  - JWT authentication
  - MongoDB integration
  - Swagger documentation
  - Redis caching
  - Weather API integration
  - Google OAuth2 support
  - Sentiment analysis
  - Email notifications
  - Scheduled tasks

---

**Built with ❤️ using Spring Boot and modern Java technologies**
