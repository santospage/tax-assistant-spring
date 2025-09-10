package br.com.santospage.taxassistant.infrastructure.persistence;

/*
@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private CustomerRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new CustomerRepositoryImpl(jdbcTemplate);
    }

    @Test
    void testFindAll() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("CUSTOMER 001");
        List<Customer> mockResult = List.of(customer);

        when(jdbcTemplate.query(anyString(), any(CustomerRowMapper.class)))
                .thenReturn(mockResult);

        // Act
        List<Customer> result = repository.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("CUSTOMER 001", result.getFirst().getName());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CustomerRowMapper.class));
    }

    @Test
    void testFindByIdFound() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("CUSTOMER 002");
        List<Customer> mockResult = List.of(customer);

        when(jdbcTemplate.query(anyString(), any(CustomerRowMapper.class), eq("000002")))
                .thenReturn(mockResult);

        // Act
        Optional<Customer> result = repository.findById("000002");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("CUSTOMER 002", result.get().getName());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CustomerRowMapper.class), eq("000002"));
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(jdbcTemplate.query(anyString(), any(CustomerRowMapper.class), eq("000999")))
                .thenReturn(List.of());

        // Act
        Optional<Customer> result = repository.findById("000999");

        // Assert
        assertTrue(result.isEmpty());
        verify(jdbcTemplate, times(1))
                .query(anyString(), any(CustomerRowMapper.class), eq("000999"));
    }
}
*/