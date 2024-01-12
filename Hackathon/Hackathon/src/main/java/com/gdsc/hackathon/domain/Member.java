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
    public class Member {
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
        @JoinColumn(name = "ACCOUNT_ID", nullable = false)
        private Account account;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "STORE_ID")
        private Store store;

        @Enumerated(EnumType.STRING)
        @Column(name = "BANK", nullable = false)
        private Bank bank;

        @Builder
        public Member(String email, String password, String name, Role role, Bank bank,Account account){
            this.email = email;
            this.password = password;
            this.name = name;
            this.role = role;
            this.bank = bank;
            this.account = new Account();
        }

    }
