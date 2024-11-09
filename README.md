# LabReport-API

## Proje Tanımı

**LabReport-API**, bir laboratuvar raporlama sistemi için geliştirilen bir RESTful API uygulamasıdır. Bu sistem, laboratuvar çalışanlarının ve yöneticilerinin raporları oluşturmasını, düzenlemesini, görüntülemesini ve silmesini sağlar. Proje, kullanıcı yönetimi, laborant (çalışan) yönetimi ve rapor işlemleri gibi işlevleri içerir.

## Kullanılan Teknolojiler ve İşlevler

Projemde kullanılan başlıca teknolojiler ve bu teknolojilerin sağladığı işlevler şunlardır:

- **Java 21** ile geliştirme yaparak, en yeni özelliklerden ve güvenlik iyileştirmelerinden faydalandım.
- **Spring Web** ile RESTful API'ler oluşturup, HTTP istekleriyle etkileşim sağladım.
- **Spring Security & JWT** ile kimlik doğrulama ve yetkilendirme işlemleri gerçekleştirdim. Kullanıcılar, admin veya employee olarak iki farklı rol ile yönetildi. Adminler, `/admin` endpointlerine erişebilirken, employee kullanıcıları belirli işlemleri yapabiliyor. Kullanıcılar başarılı bir şekilde giriş yaptıktan sonra **JWT token** döndürülür. Bu token, frontend tarafından alınarak, her API isteği için HTTP header'ına eklenir. Böylece, her istekle birlikte kullanıcı kimliği doğrulanır ve sadece yetkili kullanıcıların erişebileceği endpoint'lere güvenli bir şekilde ulaşılabilir.
- **Maven** ile proje bağımlılıklarını yönetip, derleme ve dağıtım işlemlerini otomatikleştirdim.
- **Lombok** kullanarak, Java sınıflarında gereksiz boilerplate kodlarını azalttım ve `@Getter`, `@Setter`, `@RequiredArgsConstructor` gibi anotasyonlarla kodun daha temiz görünmesini sağladım.
- **H2 Database** ile hızlı bir geliştirme ortamı kurarak, veritabanı yapılandırmasını kolaylaştırdım ve in-memory H2 veritabanı kullandım.
- **Validation (Hibernate Validator)** ile kullanıcı giriş verilerinin doğruluğunu `@NotNull`, `@Pattern` gibi anotasyonlarla kontrol ettim. 
- **JPA (Java Persistence API)** ile SQL sorguları yazma zorunluluğunu ortadan kaldırarak, veritabanı işlemlerini daha basit ve etkili hale getirdim.

Ayrıca, **dependency injection** ve **constructor bazlı bağımlılık yönetimi** kullanarak kodun daha düzenli ve okunabilir olmasını sağladım. Bu sayede, sınıflar arası bağımlılıklar daha belirgin hale geldi ve test edilebilirlik arttı.

**GlobalExceptionHandler** kullanarak uygulamadaki hataların daha düzgün bir şekilde yönetilmesini ve kullanıcı dostu mesajlar ile geri bildirim verilmesini sağladım. Bu yapı, hata ayıklama sürecini kolaylaştırırken, API kullanıcılarına anlamlı ve tutarlı hata mesajları sunar.


## Projeyi Başlatma
1. Proje dizinine gidin:
   - Windows ve macOS için:
     ```bash
     cd C:\path\to\LabReport-API
     ```
   - macOS için:
     ```bash
     cd /path/to/LabReport-API
     ```
2. Maven Wrapper ile projeyi derleyin:
   - Windows için:
     ```bash
     mvnw clean install
     ```
   - macOS için:
     ```bash
     ./mvnw clean install
     ```
3. Projeyi başlatın:
   - Windows için:
     ```bash
     mvnw spring-boot:run
     ```
   - macOS için:
     ```bash
     ./mvnw spring-boot:run
     ```

## Backend (API) Endpoint'leri

| HTTP Yöntemi | Endpoint                                | Açıklama                                                                 | Kullanım Örneği                                                    |
|------------------|--------------------------------------------|--------------------------------------------------------------------------------|-------------------------------------------------------------------------|
| PUT              | /admin/makeAdmin/{UserId}                 | Verilen `UserId`'ye sahip kullanıcıyı admin yapar.                             | `PUT /admin/makeAdmin/123` (Kullanıcı ID 123 admin yapılır)            |
| GET              | /admin/getAllUsers                        | Tüm kullanıcıların listesini döner.                                            | `GET /admin/getAllUsers` (Tüm kullanıcılar döner)                       |
| PUT              | /admin/removeAdmin/{UserId}               | Verilen `UserId`'ye sahip kullanıcının admin yetkilerini kaldırır.             | `PUT /admin/removeAdmin/123` (Kullanıcı ID 123'in adminliği kaldırılır) |
| POST             | /admin/createLaborant                     | Yeni bir laborant oluşturur.                                                   | `POST /admin/createLaborant` (Yeni laborant ekler)                      |
| DELETE           | /admin/deleteLaborant/{laborantId}        | Verilen `laborantId`'ye sahip laborantı siler.                                 | `DELETE /admin/deleteLaborant/456` (Laborant ID 456 silinir)           |
| POST             | /auth/login                               | Verilen `LoginRequest` ile kullanıcıyı giriş yapar.                            | `POST /auth/login` (Giriş yapma işlemi)                                 |
| POST             | /auth/register                            | Yeni bir kullanıcıyı `RegisterRequest` ile kaydeder.                            | `POST /auth/register` (Yeni kullanıcı kaydı yapılır)                    |
| GET              | /laborant/get/{laborantId}                | Verilen `laborantId` ile laborantı getirir.                                     | `GET /laborant/get/789` (Laborant ID 789 bilgileri döner)               |
| GET              | /laborant/getAll                          | Tüm laborantları döner.                                                        | `GET /laborant/getAll` (Tüm laborantları listele)                       |
| POST             | /rapor/create                             | Yeni bir rapor oluşturur. `laborantId` parametre olarak gereklidir.            | `POST /rapor/create?laborantId=123` (Yeni rapor oluşturulup laborant 123'e atanır) |
| DELETE           | /rapor/delete/{raporId}                   | Verilen `raporId` ile raporu siler.                                            | `DELETE /rapor/delete/234` (Rapor ID 234 silinir)                       |
| GET              | /rapor/get/{raporId}                      | Verilen `raporId` ile raporu getirir.                                          | `GET /rapor/get/234` (Rapor ID 234'ün bilgileri döner)                  |
| GET              | /rapor/getAll                             | Tüm raporları döner.                                                           | `GET /rapor/getAll` (Tüm raporları listele)                             |
| PUT              | /rapor/update/{raporId}                   | Verilen `raporId` ile raporu günceller.                                        | `PUT /rapor/update/234` (Rapor ID 234 güncellenir)                      |
| GET              | /rapor                                    | `search` sorgu parametresi ile raporları arar.                                 | `GET /rapor?search=Ahmet` (Hastalarda "Ahmet" geçen raporları arar)|
| GET              | /rapor/getAllOrderedByDate                | Tarihe göre sıralanmış tüm raporları döner.                                    | `GET /rapor/getAllOrderedByDate` (Tarihe göre sıralanmış raporlar döner)|
