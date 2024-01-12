    package com.gdsc.hackathon.domain;

    import jakarta.persistence.*;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Setter
    @Getter
    @NoArgsConstructor
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "USER_ID")
        private Long id;

        @Column(name = "USER_EMAIL")
        private String email;

        @Column(name = "USER_PASSWORD")
        private String password;

        @Column(name = "USER_NAME", nullable = false)
        private String name;


        @Enumerated(EnumType.STRING)
        @Column(name = "USER_ROLE", nullable = false)
        private Role role;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ACCOUNT_ID")
        private Account account;

        @Builder
        public User(String email, String password, String name,Role role, Account account){
            this.email = email;
            this.password = password;
            this.name = name;
            this.role = role;
            this.account = new Account();
        }

    }
