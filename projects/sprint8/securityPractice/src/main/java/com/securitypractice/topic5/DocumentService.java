package com.securitypractice.topic5;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

//    @PostAuthorize("returnObject.owner.username == authentication.name or hasRole('ADMIN')")
//    public Document getDocument(Long documentId) {
//        return documentRepository.findById(documentId)
//            .orElseThrow(() -> new DocumentNotFoundException(documentId));
//    }
}
