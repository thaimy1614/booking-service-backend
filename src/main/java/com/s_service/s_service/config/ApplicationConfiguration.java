package com.s_service.s_service.config;

import com.s_service.s_service.model.Account;
import com.s_service.s_service.model.Category;
import com.s_service.s_service.model.Role;
import com.s_service.s_service.model.Service;
import com.s_service.s_service.repository.AccountRepository;
import com.s_service.s_service.repository.CategoryRepository;
import com.s_service.s_service.repository.RoleRepository;
import com.s_service.s_service.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class ApplicationConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;

    @Bean
    ApplicationRunner applicationRunner(AccountRepository accountRepository) {
        return args -> {
            // Default student role
            if (!roleRepository.existsById(1)) {
                roleRepository.save(
                        Role.builder()
                                .id(1)
                                .role(Role.UserRole.ADMIN)
                                .description("ADMIN ROLE")
                                .build()
                );
                roleRepository.save(
                        Role.builder()
                                .id(2)
                                .role(Role.UserRole.STAFF)
                                .description("STAFF ROLE")
                                .build()
                );
                roleRepository.save(
                        Role.builder()
                                .id(3)
                                .role(Role.UserRole.CUSTOMER)
                                .description("CUSTOMER ROLE")
                                .build()
                );
            }

            // Default admin account
            if (accountRepository.findByUsername("admin").isEmpty()) {
                //admin account
                Account account = Account
                        .builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123"))
                        .roles(roleRepository.findById(1).orElse(Role.builder().role(Role.UserRole.ADMIN).description("ADMIN ROLE").build()))
                        .status(Account.AccountStatus.ACTIVE)
                        .build();
                accountRepository.save(account);
            }

            if (categoryRepository.findAll().isEmpty()) {
                categoryRepository.save(
                        Category.builder()
                                .name("Cloud Computing")
                                .description("Cloud computing refers to the delivery of computing services—such as storage, " +
                                        "processing power, and networking—over the internet (\"the cloud\"). " +
                                        "Instead of owning and maintaining physical servers or storage devices, " +
                                        "individuals and businesses can access resources on demand through a cloud provider. " +
                                        "This has transformed many aspects of daily life and business operations.")
                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("Technical Service")
                                .description("Technical services refer to specialized support and solutions provided to individuals, " +
                                        "organizations, or businesses to address specific technical needs. " +
                                        "These services cover a broad spectrum of fields such as IT, engineering, " +
                                        "telecommunications, software development, and other technology-driven industries. " +
                                        "The goal is to ensure that systems, hardware, software, and networks function efficiently " +
                                        "and that users have the support they need to manage complex technical challenges.")
                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("IT Infrastructure")
                                .description("IT Infrastructure refers to the underlying framework or foundation of " +
                                        "hardware, software, networks, data centers, and services required to deliver " +
                                        "and manage IT services in an organization. " +
                                        "It includes all components necessary for computing, storage, data management, networking, and security. " +
                                        "IT infrastructure plays a critical role in ensuring that an organization's technology systems support " +
                                        "its business processes, enabling smooth operations, productivity, and scalability.")
                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("Information Security")
                                .description("Information Security (InfoSec) refers to the processes, tools, " +
                                        "and practices designed to protect sensitive and valuable data from unauthorized access, modification, destruction, or disclosure. " +
                                        "Its primary goal is to ensure the confidentiality, integrity, and availability of information, often referred to as the CIA triad.")
                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("Data Service")
                                .description("Data Services refer to a set of services designed to manage, process, store, " +
                                        "and transmit data across different platforms and applications. " +
                                        "These services form the backbone of modern data-driven organizations, " +
                                        "enabling them to leverage information for decision-making, operations, and digital transformation.")
                                .build()
                );
            }

            if (serviceRepository.findAll().isEmpty()) {
                serviceRepository.save(
                        Service.builder()
                                .name("Brand Research")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Brand Research is the process of collecting and analyzing data " +
                                        "about a brand's identity, market positioning, customer perceptions, and overall performance. " +
                                        "It helps businesses understand how their brand is perceived by customers, identify strengths " +
                                        "and weaknesses, and develop strategies for improving brand recognition, loyalty, and market share.")
                                .handleTime(3)
                                .price(5000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Online Survey")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("An online survey is a digital questionnaire designed " +
                                        "to collect information from respondents over the internet. " +
                                        "Online surveys are widely used in various industries " +
                                        "for market research, customer feedback, academic research, and employee engagement. " +
                                        "They offer a quick, scalable, and cost-effective way to gather large amounts of data from a broad audience.")
                                .handleTime(2)
                                .price(2000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Strategic Consulting")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Strategic consulting involves helping businesses define long-term strategies " +
                                        "and identifying opportunities for growth, improvement, and competitive advantage. " +
                                        "Consultants analyze market trends, operational efficiency, and organizational structure to provide actionable insights.")
                                .handleTime(5)
                                .price(10000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Storage")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Storage services provide scalable and secure data storage solutions, " +
                                        "ranging from cloud-based storage to on-premises options. These services ensure " +
                                        "data is accessible, durable, and protected from loss or unauthorized access.")
                                .handleTime(4)
                                .price(3000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Compute")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Compute services offer the processing power necessary to run applications, " +
                                        "perform data analysis, and handle large-scale computing tasks in cloud environments. " +
                                        "These services allow businesses to scale computational resources based on demand.")
                                .handleTime(3)
                                .price(4000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Developer Tools")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Developer tools encompass a wide range of software and services that support the " +
                                        "development, testing, deployment, and management of applications. These tools streamline " +
                                        "the development lifecycle, making coding, debugging, and collaboration easier and more efficient.")
                                .handleTime(2)
                                .price(1500000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Networking & Content Delivery")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Networking and content delivery services enable businesses to deliver web content, " +
                                        "media, and applications quickly and securely across the globe. Content delivery networks (CDNs) " +
                                        "optimize latency, while networking solutions ensure reliable communication between devices and systems.")
                                .handleTime(3)
                                .price(3500000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Management Tools")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Management tools help businesses monitor, manage, and automate tasks in IT infrastructure, " +
                                        "ensuring optimal performance, resource allocation, and operational efficiency. They provide " +
                                        "insight into system health and allow for proactive issue resolution.")
                                .handleTime(3)
                                .price(2500000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Analysis")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Analysis services offer tools and techniques for analyzing business data, identifying trends, " +
                                        "and making informed decisions. These services include everything from big data analytics " +
                                        "to business intelligence platforms that provide actionable insights.")
                                .handleTime(4)
                                .price(5000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Desktop & App Streaming")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Desktop and app streaming services allow users to remotely access applications " +
                                        "and desktops from any device with an internet connection. These services are widely used " +
                                        "in virtual work environments and for delivering software to end users without the need for local installation.")
                                .handleTime(2)
                                .price(3000000)
                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("Security Configuration Assessment")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Security configuration assessment reviews an organization's IT systems " +
                                        "to ensure they are configured securely. It identifies potential vulnerabilities, " +
                                        "misconfigurations, and compliance issues, ensuring that security best practices are followed.")
                                .handleTime(4)
                                .price(6000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Penetration Testing")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Penetration testing, also known as ethical hacking, simulates cyberattacks " +
                                        "to assess the security of an IT system. This helps identify and fix vulnerabilities " +
                                        "before they can be exploited by malicious actors.")
                                .handleTime(6)
                                .price(8000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Information Security - The Shield of Enterprises in the Digital Age")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Information security protects organizations' digital assets from threats, " +
                                        "such as data breaches, cyberattacks, and unauthorized access. This service helps businesses " +
                                        "ensure the confidentiality, integrity, and availability of their data in the digital landscape.")
                                .handleTime(5)
                                .price(9000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Information Security Monitoring")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Information security monitoring involves the continuous observation and analysis of " +
                                        "an organization's IT systems to detect and respond to security threats in real time. " +
                                        "It helps businesses maintain a secure environment and swiftly address potential incidents.")
                                .handleTime(3)
                                .price(7500000)
                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("IT Support")
                                .category(categoryRepository.findById(3).orElse(null))
                                .description("IT Support provides technical assistance for organizations, helping to troubleshoot " +
                                        "and resolve issues related to hardware, software, networks, and security. IT support services ensure " +
                                        "smooth operations and minimal downtime for critical systems.")
                                .handleTime(2)
                                .price(3000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Consulting on Building Security Monitoring Systems")
                                .category(categoryRepository.findById(3).orElse(null))
                                .description("Consulting on building security monitoring systems involves advising businesses on the " +
                                        "design, implementation, and management of security monitoring infrastructure. These services help " +
                                        "ensure that organizations are able to detect and respond to potential security threats in real time.")
                                .handleTime(5)
                                .price(12000000)
                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("Consulting on Building an Information Security Management System")
                                .category(categoryRepository.findById(3).orElse(null))  // Adjust category ID as needed
                                .description("Consulting on building an Information Security Management System (ISMS) involves " +
                                        "guiding organizations in establishing, implementing, maintaining, and continuously improving " +
                                        "a security management system to protect sensitive data and ensure compliance with security standards.")
                                .handleTime(5)
                                .price(15000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Supporting Security System Administration")
                                .category(categoryRepository.findById(4).orElse(null))  // Adjust category ID as needed
                                .description("Supporting security system administration involves managing and maintaining an organization's " +
                                        "security infrastructure, including firewalls, intrusion detection systems, and security monitoring tools. " +
                                        "This service ensures that security systems operate efficiently and respond to emerging threats.")
                                .handleTime(3)
                                .price(8000000)
                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("Database System Technical Support")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("Technical support for database systems includes troubleshooting, maintenance, and performance monitoring. " +
                                        "This service ensures the stability and efficiency of database operations while addressing any technical issues that arise.")
                                .handleTime(3)
                                .price(6000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Business Continuity & Disaster Recovery")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("Business Continuity and Disaster Recovery (BC/DR) services help organizations plan for " +
                                        "and respond to potential disruptions. This service ensures that critical business functions remain operational during a crisis.")
                                .handleTime(7)
                                .price(15000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Data System Upgrade and Conversion")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("Data system upgrade and conversion services involve migrating databases, upgrading systems, " +
                                        "and ensuring data integrity during transitions. This service supports businesses in modernizing their data infrastructure.")
                                .handleTime(4)
                                .price(10000000)
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Proactively Warn of Database Operating Status")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("This service proactively monitors the operating status of a database system, providing real-time alerts " +
                                        "on potential issues such as performance degradation, security risks, and system failures. It helps businesses take preventive measures.")
                                .handleTime(2)
                                .price(5000000)
                                .build()
                );
            }
        };
    }
}
