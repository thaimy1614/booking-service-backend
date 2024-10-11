package com.s_service.s_service.config;

import com.s_service.s_service.model.*;
import com.s_service.s_service.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class ApplicationConfiguration {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;
    private final StageRepository stageRepository;

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
                                .description("Điện toán đám mây đề cập đến việc cung cấp các dịch vụ máy tính—chẳng hạn như lưu trữ, "
                                        + "sức mạnh xử lý và mạng—qua internet. "
                                        + "Thay vì sở hữu và duy trì các máy chủ hoặc thiết bị lưu trữ vật lý, "
                                        + "cá nhân và doanh nghiệp có thể truy cập tài nguyên theo yêu cầu thông qua một nhà cung cấp đám mây. "
                                        + "Điều này đã chuyển đổi nhiều khía cạnh trong cuộc sống hàng ngày và hoạt động kinh doanh.")
                                .benefits(List.of(
                                        "Tiết kiệm chi phí",
                                        "Khả năng mở rộng linh hoạt",
                                        "Truy cập mọi lúc, mọi nơi",
                                        "Bảo mật nâng cao"
                                ))
                                .categoryStatus(Category.CategoryStatus.AVAILABLE)
                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("Technical Service")
                                .description("Dịch vụ kỹ thuật đề cập đến sự hỗ trợ và giải pháp chuyên biệt được cung cấp cho cá nhân, tổ chức hoặc doanh nghiệp để đáp ứng nhu cầu kỹ thuật cụ thể. Những dịch vụ này bao gồm nhiều lĩnh vực như CNTT, kỹ thuật, viễn thông, phát triển phần mềm và các ngành công nghiệp công nghệ khác. Mục tiêu là đảm bảo rằng hệ thống, phần cứng, phần mềm và mạng hoạt động hiệu quả và rằng người dùng có sự hỗ trợ cần thiết để quản lý những thách thức kỹ thuật phức tạp.")
                                .benefits(List.of(
                                        "Hỗ trợ chuyên nghiệp",
                                        "Giải quyết vấn đề nhanh chóng",
                                        "Tối ưu hóa hiệu suất",
                                        "Đảm bảo tính liên tục"
                                ))
                                .categoryStatus(Category.CategoryStatus.AVAILABLE)

                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("IT Infrastructure")
                                .description("Hạ tầng CNTT đề cập đến khung hoặc nền tảng cơ sở của phần cứng, phần mềm, mạng, trung tâm dữ liệu và dịch vụ cần thiết để cung cấp và quản lý các dịch vụ CNTT trong một tổ chức. Nó bao gồm tất cả các thành phần cần thiết cho tính toán, lưu trữ, quản lý dữ liệu, mạng và bảo mật. Hạ tầng CNTT đóng vai trò quan trọng trong việc đảm bảo rằng các hệ thống công nghệ của một tổ chức hỗ trợ các quy trình kinh doanh của nó, cho phép hoạt động suôn sẻ, năng suất và khả năng mở rộng.")
                                .benefits(List.of(
                                        "Nền tảng vững chắc",
                                        "Tăng cường hiệu suất",
                                        "Bảo mật và khả năng phục hồi",
                                        "Tính linh hoạt và khả năng mở rộng"
                                ))
                                .categoryStatus(Category.CategoryStatus.AVAILABLE)

                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("Information Security")
                                .description("Bảo mật thông tin (InfoSec) đề cập đến các quy trình, công cụ và thực tiễn được thiết kế để bảo vệ dữ liệu nhạy cảm và có giá trị khỏi việc truy cập, sửa đổi, phá hủy hoặc tiết lộ trái phép. Mục tiêu chính là đảm bảo tính bảo mật, toàn vẹn và khả dụng của thông tin, thường được gọi là tam giác CIA.")
                                .benefits(List.of(
                                        "Bảo vệ dữ liệu nhạy cảm",
                                        "Tuân thủ quy định",
                                        "Giảm rủi ro",
                                        "Tăng cường uy tín"
                                ))
                                .categoryStatus(Category.CategoryStatus.AVAILABLE)

                                .build()
                );
                categoryRepository.save(
                        Category.builder()
                                .name("Data Service")
                                .description("Dịch vụ dữ liệu đề cập đến một tập hợp các dịch vụ được thiết kế để quản lý, xử lý, lưu trữ và truyền tải dữ liệu qua các nền tảng và ứng dụng khác nhau. Những dịch vụ này tạo thành nền tảng của các tổ chức hiện đại dựa trên dữ liệu, cho phép họ tận dụng thông tin cho quyết định, hoạt động và chuyển đổi số.")
                                .benefits(List.of(
                                        "Quản lý dữ liệu hiệu quả",
                                        "Hỗ trợ ra quyết định",
                                        "Tăng cường khả năng phân tích",
                                        "Thúc đẩy chuyển đổi số"
                                ))
                                .categoryStatus(Category.CategoryStatus.AVAILABLE)

                                .build()
                );

            }

            if (serviceRepository.findAll().isEmpty()) {
                serviceRepository.save(
                        Service.builder()
                                .name("Brand Research")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Nghiên cứu thương hiệu là quá trình thu thập và phân tích dữ liệu về danh tính, vị trí thị trường, nhận thức của khách hàng và hiệu suất tổng thể của một thương hiệu. Nó giúp các doanh nghiệp hiểu cách khách hàng nhìn nhận thương hiệu của họ, xác định điểm mạnh và điểm yếu, và phát triển các chiến lược để cải thiện nhận thức thương hiệu, lòng trung thành và thị phần.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)
                                .benefits(List.of(
                                        "Brand Research giúp doanh nghiệp nắm bắt được cách khách hàng nhìn nhận về thương hiệu của mình, bao gồm các yếu tố như logo, thông điệp.",
                                        "Hiểu nhận thức của khách hàng.",
                                        "Cải thiện vị trí thị trường."))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Online Survey")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Khảo sát trực tuyến là một bảng câu hỏi kỹ thuật số được thiết kế để thu thập thông tin từ người tham gia qua internet. Khảo sát trực tuyến được sử dụng rộng rãi trong các ngành khác nhau cho nghiên cứu thị trường, phản hồi của khách hàng, nghiên cứu học thuật và sự tham gia của nhân viên. Chúng cung cấp một cách nhanh chóng, mở rộng và hiệu quả về chi phí để thu thập một lượng lớn dữ liệu từ một đối tượng rộng.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Thu thập dữ liệu nhanh chóng và hiệu quả.",
                                        "Giảm chi phí nghiên cứu thị trường.",
                                        "Dễ dàng phân tích và báo cáo dữ liệu."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Strategic Consulting")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Tư vấn chiến lược liên quan đến việc giúp các doanh nghiệp xác định các chiến lược dài hạn và xác định cơ hội tăng trưởng, cải tiến và lợi thế cạnh tranh. Các chuyên gia tư vấn phân tích xu hướng thị trường, hiệu quả hoạt động và cấu trúc tổ chức để cung cấp những hiểu biết có thể hành động.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Xác định chiến lược dài hạn hiệu quả.",
                                        "Tối ưu hóa hoạt động và cấu trúc tổ chức.",
                                        "Cung cấp những hiểu biết có thể hành động."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Storage")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Dịch vụ lưu trữ cung cấp các giải pháp lưu trữ dữ liệu có khả năng mở rộng và bảo mật, từ lưu trữ trên đám mây đến các tùy chọn tại chỗ. Những dịch vụ này đảm bảo rằng dữ liệu có thể truy cập, bền vững và được bảo vệ khỏi mất mát hoặc truy cập trái phép.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Dữ liệu luôn sẵn có và bảo mật.",
                                        "Giải pháp lưu trữ linh hoạt và mở rộng.",
                                        "Đảm bảo tính bền vững của dữ liệu."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Compute")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Dịch vụ tính toán cung cấp sức mạnh xử lý cần thiết để chạy các ứng dụng, thực hiện phân tích dữ liệu và xử lý các tác vụ tính toán quy mô lớn trong các môi trường đám mây. Những dịch vụ này cho phép các doanh nghiệp mở rộng tài nguyên tính toán dựa trên nhu cầu.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Tăng cường sức mạnh xử lý cho ứng dụng.",
                                        "Mở rộng tài nguyên tính toán linh hoạt.",
                                        "Giảm thiểu thời gian xử lý và phân tích dữ liệu."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Developer Tools")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Công cụ phát triển bao gồm một loạt phần mềm và dịch vụ hỗ trợ việc phát triển, kiểm tra, triển khai và quản lý các ứng dụng. Những công cụ này đơn giản hóa chu trình phát triển, giúp việc lập trình, gỡ lỗi và cộng tác trở nên dễ dàng và hiệu quả hơn.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Giúp tối ưu hóa quy trình phát triển.",
                                        "Cải thiện khả năng hợp tác giữa các lập trình viên.",
                                        "Rút ngắn thời gian phát triển ứng dụng."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Networking & Content Delivery")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Dịch vụ mạng và phân phối nội dung cho phép các doanh nghiệp cung cấp nội dung web, phương tiện và ứng dụng một cách nhanh chóng và bảo mật trên toàn cầu. Mạng lưới phân phối nội dung (CDN) tối ưu hóa độ trễ, trong khi các giải pháp mạng đảm bảo giao tiếp đáng tin cậy giữa các thiết bị và hệ thống.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Cải thiện tốc độ tải trang web.",
                                        "Giảm thiểu độ trễ trong việc truyền tải nội dung.",
                                        "Đảm bảo tính sẵn có của nội dung."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Management Tools")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Các công cụ quản lý giúp doanh nghiệp giám sát, quản lý và tự động hóa các tác vụ trong hạ tầng CNTT, đảm bảo hiệu suất tối ưu, phân bổ tài nguyên và hiệu quả hoạt động. Chúng cung cấp cái nhìn tổng quan về tình trạng hệ thống và cho phép giải quyết vấn đề một cách chủ động.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Giám sát tình trạng hệ thống dễ dàng.",
                                        "Tối ưu hóa phân bổ tài nguyên.",
                                        "Tăng cường khả năng giải quyết vấn đề."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Analysis")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Các dịch vụ phân tích cung cấp công cụ và kỹ thuật để phân tích dữ liệu doanh nghiệp, xác định xu hướng và đưa ra quyết định thông minh. Những dịch vụ này bao gồm mọi thứ từ phân tích dữ liệu lớn đến các nền tảng trí tuệ doanh nghiệp cung cấp những hiểu biết có thể hành động.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Cung cấp những hiểu biết sâu sắc về dữ liệu.",
                                        "Giúp đưa ra quyết định dựa trên dữ liệu.",
                                        "Xác định xu hướng và cơ hội mới."
                                ))
                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Desktop & App Streaming")
                                .category(categoryRepository.findById(1).orElse(null))
                                .description("Dịch vụ phát trực tuyến desktop và ứng dụng cho phép người dùng truy cập từ xa các ứng dụng và desktop từ bất kỳ thiết bị nào có kết nối internet. Những dịch vụ này được sử dụng rộng rãi trong các môi trường làm việc ảo và cho việc cung cấp phần mềm đến tay người dùng mà không cần cài đặt tại địa phương.")
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .benefits(List.of(
                                        "Truy cập ứng dụng từ xa dễ dàng.",
                                        "Giảm thiểu chi phí cài đặt phần mềm.",
                                        "Tăng cường khả năng làm việc từ xa."
                                ))

                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("Security Configuration Assessment")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Đánh giá cấu hình bảo mật xem xét các hệ thống CNTT của một tổ chức để đảm bảo rằng chúng được cấu hình an toàn. Nó xác định các lỗ hổng tiềm ẩn, cấu hình sai và vấn đề tuân thủ, đảm bảo rằng các thực tiễn tốt nhất về bảo mật được thực hiện.")
                                .benefits(List.of(
                                        "Giảm thiểu rủi ro bảo mật.",
                                        "Cải thiện tuân thủ quy định.",
                                        "Bảo vệ thông tin nhạy cảm."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Penetration Testing")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Kiểm tra xâm nhập, còn được gọi là hacking đạo đức, mô phỏng các cuộc tấn công mạng để đánh giá bảo mật của một hệ thống CNTT. Điều này bao gồm việc xác định các lỗ hổng trong phần mềm, mạng và các quy trình, giúp các tổ chức tăng cường bảo mật.")
                                .benefits(List.of(
                                        "Phát hiện lỗ hổng bảo mật trước khi bị tấn công.",
                                        "Cải thiện chiến lược an ninh mạng.",
                                        "Tăng cường sự tự tin trong bảo mật hệ thống."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Information Security - The Shield of Enterprises in the Digital Age")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Bảo mật thông tin bảo vệ tài sản kỹ thuật số của tổ chức khỏi các mối đe dọa, " +
                                        "như vi phạm dữ liệu, tấn công mạng và truy cập trái phép. Dịch vụ này giúp doanh nghiệp " +
                                        "đảm bảo tính bảo mật, toàn vẹn và khả năng truy cập dữ liệu trong môi trường kỹ thuật số.")
                                .benefits(List.of(
                                        "Bảo vệ thông tin nhạy cảm.",
                                        "Giảm thiểu rủi ro về an ninh mạng.",
                                        "Tăng cường niềm tin của khách hàng."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Information Security Monitoring")
                                .category(categoryRepository.findById(2).orElse(null))
                                .description("Giám sát bảo mật thông tin liên quan đến việc quan sát và phân tích liên tục " +
                                        "các hệ thống CNTT của tổ chức để phát hiện và ứng phó với các mối đe dọa bảo mật theo thời gian thực. " +
                                        "Nó giúp doanh nghiệp duy trì một môi trường an toàn và nhanh chóng giải quyết các sự cố tiềm ẩn.")
                                .benefits(List.of(
                                        "Phát hiện và ứng phó kịp thời với các mối đe dọa.",
                                        "Duy trì an ninh liên tục cho hệ thống CNTT.",
                                        "Tăng cường khả năng phản ứng trước sự cố."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("IT Support")
                                .category(categoryRepository.findById(3).orElse(null))
                                .description("Hỗ trợ CNTT cung cấp sự trợ giúp kỹ thuật cho các tổ chức, giúp khắc phục " +
                                        "và giải quyết các vấn đề liên quan đến phần cứng, phần mềm, mạng và bảo mật. Dịch vụ hỗ trợ CNTT đảm bảo " +
                                        "hoạt động trơn tru và thời gian ngừng hoạt động tối thiểu cho các hệ thống quan trọng.")
                                .benefits(List.of(
                                        "Giải quyết vấn đề nhanh chóng.",
                                        "Đảm bảo hệ thống hoạt động liên tục.",
                                        "Tăng cường hiệu suất làm việc của nhân viên."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Consulting on Building Security Monitoring Systems")
                                .category(categoryRepository.findById(3).orElse(null))
                                .description("Tư vấn xây dựng hệ thống giám sát bảo mật liên quan đến việc tư vấn cho doanh nghiệp về " +
                                        "thiết kế, triển khai và quản lý cơ sở hạ tầng giám sát bảo mật. Các dịch vụ này giúp " +
                                        "đảm bảo rằng các tổ chức có khả năng phát hiện và ứng phó với các mối đe dọa bảo mật tiềm ẩn theo thời gian thực.")
                                .benefits(List.of(
                                        "Tối ưu hóa thiết kế hệ thống bảo mật.",
                                        "Đảm bảo tính sẵn có của hệ thống giám sát.",
                                        "Cải thiện khả năng phát hiện sự cố."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("Consulting on Building an Information Security Management System")
                                .category(categoryRepository.findById(3).orElse(null))  // Adjust category ID as needed
                                .description("Tư vấn xây dựng Hệ thống Quản lý Bảo mật Thông tin (ISMS) liên quan đến " +
                                        "hướng dẫn các tổ chức thiết lập, triển khai, duy trì và cải tiến liên tục " +
                                        "một hệ thống quản lý bảo mật để bảo vệ dữ liệu nhạy cảm và đảm bảo tuân thủ các tiêu chuẩn bảo mật.")
                                .benefits(List.of(
                                        "Đảm bảo tuân thủ các tiêu chuẩn bảo mật.",
                                        "Bảo vệ thông tin nhạy cảm và tài sản của tổ chức.",
                                        "Cải thiện quy trình quản lý an ninh."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Supporting Security System Administration")
                                .category(categoryRepository.findById(4).orElse(null))  // Adjust category ID as needed
                                .description("Hỗ trợ quản trị hệ thống bảo mật liên quan đến việc quản lý và duy trì cơ sở hạ tầng " +
                                        "bảo mật của tổ chức, bao gồm tường lửa, hệ thống phát hiện xâm nhập và công cụ giám sát bảo mật. " +
                                        "Dịch vụ này đảm bảo rằng các hệ thống bảo mật hoạt động hiệu quả và phản ứng với các mối đe dọa mới nổi.")
                                .benefits(List.of(
                                        "Đảm bảo hoạt động hiệu quả của hệ thống bảo mật.",
                                        "Giảm thiểu rủi ro từ các mối đe dọa mới.",
                                        "Tăng cường sự bảo vệ thông tin của tổ chức."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );

                serviceRepository.save(
                        Service.builder()
                                .name("Database System Technical Support")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("Hỗ trợ kỹ thuật cho các hệ thống cơ sở dữ liệu bao gồm khắc phục sự cố, bảo trì và giám sát hiệu suất. " +
                                        "Dịch vụ này đảm bảo sự ổn định và hiệu quả của hoạt động cơ sở dữ liệu trong khi giải quyết bất kỳ vấn đề kỹ thuật nào phát sinh.")
                                .benefits(List.of(
                                        "Đảm bảo hoạt động ổn định của cơ sở dữ liệu.",
                                        "Tối ưu hóa hiệu suất hệ thống.",
                                        "Giảm thiểu thời gian ngừng hoạt động."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Business Continuity & Disaster Recovery")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("Dịch vụ Khả năng Liên tục Kinh doanh và Phục hồi Sau Thảm họa (BC/DR) giúp các tổ chức lập kế hoạch cho " +
                                        "và ứng phó với những gián đoạn tiềm tàng. Dịch vụ này đảm bảo rằng các chức năng kinh doanh quan trọng vẫn hoạt động trong thời gian khủng hoảng.")
                                .benefits(List.of(
                                        "Đảm bảo khả năng phục hồi sau thảm họa.",
                                        "Giảm thiểu thiệt hại cho tổ chức.",
                                        "Bảo vệ tài sản và tài nguyên quan trọng."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Data System Upgrade and Conversion")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("Dịch vụ nâng cấp và chuyển đổi hệ thống dữ liệu bao gồm việc di chuyển cơ sở dữ liệu, nâng cấp hệ thống, " +
                                        "và đảm bảo tính toàn vẹn của dữ liệu trong quá trình chuyển đổi. Dịch vụ này hỗ trợ doanh nghiệp trong việc hiện đại hóa hạ tầng dữ liệu của họ.")
                                .benefits(List.of(
                                        "Cải thiện hiệu suất hệ thống dữ liệu.",
                                        "Đảm bảo tính toàn vẹn dữ liệu trong quá trình chuyển đổi.",
                                        "Hỗ trợ doanh nghiệp trong việc hiện đại hóa cơ sở hạ tầng."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
                serviceRepository.save(
                        Service.builder()
                                .name("Proactively Warn of Database Operating Status")
                                .category(categoryRepository.findById(5).orElse(null))  // Adjust category ID as needed
                                .description("Dịch vụ này theo dõi một cách chủ động tình trạng vận hành của hệ thống cơ sở dữ liệu, cung cấp các cảnh báo theo thời gian thực " +
                                        "về các vấn đề tiềm ẩn như suy giảm hiệu suất, rủi ro bảo mật và sự cố hệ thống. Nó giúp doanh nghiệp thực hiện các biện pháp phòng ngừa.")
                                .benefits(List.of(
                                        "Giảm thiểu rủi ro và gián đoạn.",
                                        "Cải thiện hiệu suất hệ thống.",
                                        "Tăng cường khả năng kiểm soát hệ thống."
                                ))
                                .serviceStatus(Service.ServiceStatus.AVAILABLE)

                                .build()
                );
            }
            if (stageRepository.findAll().isEmpty()) {
                stageRepository.save(
                        Stage.builder()
                                .id(1)
                                .name("Tiếp nhận yêu cầu")
                                .build()
                );
                stageRepository.save(
                        Stage.builder()
                                .id(2)
                                .name("Xác nhận yêu cầu")
                                .build()
                );
                stageRepository.save(
                        Stage.builder()
                                .id(3)
                                .name("Xử lí yêu cầu")
                                .build()
                );
                stageRepository.save(
                        Stage.builder()
                                .id(4)
                                .name("Hoàn thành")
                                .build()
                );
            }
        };
    }
}
