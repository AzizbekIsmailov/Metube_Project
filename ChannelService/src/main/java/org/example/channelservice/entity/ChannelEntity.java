package org.example.channelservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import metube.com.dto.BaseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "channels")
public class ChannelEntity extends BaseEntity {
    private String name;
}
