package com.task.grievancesystem.entity;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "grievance")
public class grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String type;


    private String descptn;
    private String status = "unassigned";

    @Column(name = "usr_id", nullable = false)  // Ensures this cannot be null
    private Long usrId;


    @Column(name = "asgn_id")
    private Long asgn;


    public grievance() {
    }

    public grievance(String type, String descptn){

        this.type=type;
        this.descptn=descptn;
        this.status=status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescptn() {
        return descptn;
    }

    public void setDesc(String descptn) {
        this.descptn = descptn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUsrId() {
        return usrId;
    }

    public void setUsrId(Long usrId) {

        this.usrId = usrId;
    }

    public Long getAsgn() {
        return asgn;
    }

    public void setAsgn(Long asgn) {
        this.asgn = asgn;
    }
}
