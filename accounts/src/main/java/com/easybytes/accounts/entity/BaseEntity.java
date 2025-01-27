package com.easybytes.accounts.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity {

  @CreatedDate
  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private String createdBy;

  @LastModifiedDate
  @Column(name = "updated_at", insertable = false)
  private LocalDateTime updatedAt;

  @LastModifiedBy
  @Column(name = "updated_by", insertable = false)
  private String updatedBy;

//  @PrePersist
//  public void onPrePersist() {
//    System.out.println("Inside prePersist");
//    setCreatedAt(LocalDateTime.now());
//    setCreatedBy("Niyank Bam");
//  }
//
//  @PreUpdate
//  public void onPreUpdate() {
//    System.out.println("Inside preUpdate");
//    setUpdatedAt(LocalDateTime.now());
//    setUpdatedBy("Niyank Bam");
//  }
}
