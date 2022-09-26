package com.example.getawaydrives.utility;

import com.example.getawaydrives.entities.Document;
import com.example.getawaydrives.entities.DocumentType;
import com.example.getawaydrives.repositories.DocumentRepository;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;

public class CommonMethods {

    public static void createDocument(DocumentRepository repo, MultipartFile[] file, Integer userId, Integer vehicleId,
                                      int docType) {
        try {
            for(MultipartFile f: file) {
                Document document = new Document();
                if(userId != null){
                    document.setUserId(userId);
                }
                if (vehicleId != null) {
                    document.setVehicleId(vehicleId);
                }
                document.setName(f.getOriginalFilename());
                document.setContent(f.getBytes());
                document.setDocumentType(docType);
                document.setCreatedBy(userId);
                document.setCreatedOn(new Date());
                document.setLastUpdatedBy(userId);
                document.setLastUpdatedOn(new Date());
                repo.save(document);
            }
        }catch(IOException e){
            System.out.println(e.getMessage()); //TODO show this message
        }

    }
}
