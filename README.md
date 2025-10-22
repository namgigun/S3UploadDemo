## AWS S3 파일 업로드 & 삭제 토이 프로젝트
AWS S3를 이용한 **파일 업로드 및 삭제 기능**을 직접 구현해보기 위한 토이 프로젝트입니다.

<br/>

## ⚙️ 개발환경

| 항목 | 내용 |
|------|------|
| **Language** | Java |
| **Framework** | Spring Boot |
| **Infra** | AWS S3 |
| **IDE** | IntelliJ IDEA |

<br/>

## 🚀 핵심 기능
- 파일 업로드 (S3 버킷 저장)
- 파일 삭제 (S3 오브젝트 삭제)

<br/>

## 📄 프로젝트 시나리오
### 🔹 처리 흐름
```
Client → Spring Boot (FileController) → S3 Bucket
```
<br/>


### 🔹 파일 업로드 시나리오

1. 사용자는 multipart/form-data 형식으로 파일 업로드 요청
2. SpringBoot 서버를 통해 S3 버킷에 저장
3. 업로드된 파일의 S3 Public URL을 사용자에게 반환

<br/>

### 🔹 파일 삭제 시나리오

1. 사용자는 S3에 저장된 파일이름과 함께 파일 삭제 요청
2. SppringBoot 서버는 해당 파일이름을 가진 S3 오브젝트의 존재를 체크
3. 해당 오브젝트가 존재하다면 삭제
4. 삭제된 파일이름을 사용자에게 반환

<br/>

## 💻 핵심 구현

### 📁 파일 업로드 코드
```java

public String uploadFile(MultipartFile multipartFile) {
    if (multipartFile == null || multipartFile.isEmpty()) {
        return null;
    }

    String fileName = createFileName(multipartFile.getOriginalFilename());
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(multipartFile.getSize());
    objectMetadata.setContentType(multipartFile.getContentType());

    String publicURL = null;
    try(InputStream inputStream = multipartFile.getInputStream()) {
        // S3 버킷에 해당 인자로 받은 이미지를 업로드
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata));

        // 업로드한 이미지의 PublicURL 정보
        publicURL = amazonS3.getUrl(bucket, fileName).toString();
    } catch (IOException e){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
    }

    return publicURL;
}
```

<br/>


### 🗑 파일 삭제 코드
```java
public void deleteFile(String fileName) {
    if(!amazonS3.doesObjectExist(bucket, fileName)) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 삭제에 실패했습니다.");
    }

    amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
}
```

<br/>

## ✅ 결과 (Postman 테스트)

### 📤 파일 업로드
<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/9dc13f64-da4b-495d-935e-85047dcbe3ac" />
<br/>

### 🧹 파일 삭제
<img width="500" height="500" alt="image" src="https://github.com/user-attachments/assets/6d422610-4ace-4283-a88f-cf26c5881ed0" />
