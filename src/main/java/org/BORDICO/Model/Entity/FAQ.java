package org.BORDICO.Model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.BORDICO.Model.Enum.FAQCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "faqs")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class FAQ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "question",length = 200)
    private String question;
    @Column(name = "answer",length = 200)
    private String answer;
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private FAQCategory faqCategory;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
