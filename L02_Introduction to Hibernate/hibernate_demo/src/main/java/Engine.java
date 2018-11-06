import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class Engine implements Runnable {
    private EntityManager entityManager;

    protected Engine(EntityManager manager) {
        this.entityManager = manager;
    }

    public void run() {
        //Please write here the method of the task you want to test:
        //Some methods needs try catch block, because I use it BufferedReader.
        //Example:
//        try {
//            this.containsEmployee();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 2.	Remove Objects
     * Use the soft_uni database. Persist all towns from the database. Detach those whose name length is more than 5 symbols.
     * Then transform the names of all attached towns to lowercase and save them to the database.**/
    private void removeObjects() {
       this.entityManager.getTransaction().begin();

         List<Town> townList = this.entityManager.
                createQuery("FROM Town  WHERE char_length(name) > 5", Town.class)
                .getResultList();

        for (Town town : townList) {
            town.setName(town.getName().toLowerCase());
        }

        this.entityManager.getTransaction().commit();
    }

    /**
     * 3.	Contains Employee
     * Use soft_uni database.
     * Write a program that check if given employee name as an input is contained in the database.*/
    private void containsEmployee() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentName = reader.readLine();
        this.entityManager.getTransaction().begin();

        List<Employee> employeeList = this.entityManager.createQuery("FROM Employee WHERE concat(first_name, ' ', last_name) = :name", Employee.class)
                .setParameter("name", currentName).getResultList();

        if (employeeList.isEmpty()){
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

        this.entityManager.getTransaction().commit();
    }

    /**
     * 4.	Employees with Salary Over 50 000
     * Write a program to get the first name of all employees who have salary over 50 000.*/
    private void employeeWithOverSalary() {
        this.entityManager.getTransaction().begin();

        List<Employee> employeeList = this.entityManager.createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList();

        employeeList.forEach(e -> System.out.println(e.getFirstName()));

        this.entityManager.getTransaction().commit();
    }

    /**
     * 5. Employees from Department
     * Extract all employees from the Research and Development department. Order them by salary (in ascending order),
     * then by id (in asc order). Print only their first name, last name, department name and salary. */
    private void employeesFromDepartment() {
        this.entityManager.getTransaction().begin();

        List<Employee> employeeList = this.entityManager.createQuery("FROM Employee WHERE department = 6", Employee.class)
                .getResultList();

        employeeList.stream().sorted((x1, x2) -> {
            int comp = x1.getSalary().compareTo(x2.getSalary());
            return comp;
        }).forEach(x -> System.out.println(String.format("%s %s from %s - $%.2f",
                x.getFirstName(), x.getLastName(), x.getDepartment().getName(), x.getSalary())));

        this.entityManager.getTransaction().commit();
    }

    /**
     * 5.	Adding a New Address and Updating Employee
     * Create a new address with text "Vitoshka 15".
     * Set that address to an employee with last name from user input.*/
    private void addingNewAddressAndUpdating() throws IOException {
        this.entityManager.getTransaction().begin();
        Address address = new Address();
        address.setText("Vitoshka 15");

        Town town = new Town();
        town.setName("Sofia");
        address.setTown(town);

        this.entityManager.persist(town);
        this.entityManager.persist(address);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastName = reader.readLine();

        Employee employee = this.entityManager.createQuery
                ("SELECT e FROM Employee AS e WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", lastName)
                .getSingleResult();

        this.entityManager.detach(employee.getAddress());
        employee.setAddress(address);
        this.entityManager.merge(employee);

        this.entityManager.getTransaction().commit();
    }

    /**
     * 6.	Addresses with Employee Count
     * Find all addresses, ordered by the number of employees who live there (descending),
     * then by town id (ascending).
     * Take only the first 10 addresses and print their address text, town name and employee count. */
    private void addressesWithEmployeeCount() {
        this.entityManager.getTransaction().begin();

        List<Address> addressList = this.entityManager.createQuery("SELECT a FROM Address AS a ORDER BY a.employees.size DESC, a.town.id", Address.class)
                .setMaxResults(10)
                .getResultList();

        addressList.forEach(x -> System.out.println(String.format("%s %s - %d employees",
                x.getText(), x.getTown().getName(), x.getEmployees().size())));

        this.entityManager.getTransaction().commit();
    }

    /**
     * 7.	Get Employee with Project
     * Get an employee by id. Print only his/her first name, last name, job title and projects (only their names).
     * The projects should be ordered by name (ascending). The output should be printed in the format given in example
     */
    private void getEmployeeWithProject() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int currentID = Integer.parseInt(reader.readLine());
        this.entityManager.getTransaction().begin();

        List<Employee> employeeList = this.entityManager.createQuery("SELECT e FROM Employee AS e WHERE e.id = :id", Employee.class)
                .setParameter("id", currentID)
                .getResultList();

        if (employeeList.isEmpty()){
            System.out.println("Please insert valid ID");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Employee employee : employeeList) {
            sb.append(String.format("%s %s - %s%n",
                    employee.getFirstName(), employee.getLastName(), employee.getJobTitle()));

            employee.getProjects().stream().sorted((x1, x2) -> {
                int comp =  x1.getName().compareTo(x2.getName());
                return comp;
            }).forEach(x -> sb.append("      ").append(x.getName()).append(System.lineSeparator()));
        }

        System.out.println(sb.toString());
        this.entityManager.getTransaction().commit();
    }

    /**
     * 8.	Find Latest 10 Projects
     * Write a program that prints last 10 started projects. Print their name, description,
     * start and end date and sort them by name lexicographically.
     * See example for output format.
     */
    private void findLastTenProjects() {
        StringBuilder sb = new StringBuilder();
        this.entityManager.getTransaction().begin();

        List<Project> projectList = this.entityManager.createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projectList.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(x -> sb.append(String.format("Project name: %s%n",  x.getName()))
                .append(String.format("     Project Description: %s", x.getDescription())).append(System.lineSeparator())
                .append(String.format("     Project Start Date: %s", x.getStartDate())).append(System.lineSeparator())
                .append(String.format("     Project End Date: %s", x.getEndDate())).append(System.lineSeparator()));

        System.out.println(sb.toString());
        this.entityManager.getTransaction().commit();
    }

    /**
     * 9.	Increase Salaries
     * Write a program that increases salaries of all employees that are in the Engineering,
     * Tool Design, Marketing or Information Services department by 12%.
     * Then print first name, last name and salary for those employees whose salary was increased.*/
    private void increaseSalaries(){
     this.entityManager.getTransaction().begin();

        List<Employee> employeeList = this.entityManager.createQuery("SELECT e FROM Employee AS e WHERE e.department.name IN " +
                        "('Tool Design','Marketing', 'Information Services', 'Engineering')" , Employee.class)
                        .getResultList();

        for (Employee employee : employeeList) {
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
        }

        employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n",
                        e.getFirstName(), e.getLastName(), e.getSalary()));

        this.entityManager.getTransaction().commit();
     }


     /**
      * 10.	Remove Towns
      * Write a program that deletes town which name is given as an input. Also delete all addresses that are in those towns.
      * Print on the console the number addresses that were deleted as given in the example:*/
     private void deleteTowns() throws IOException {
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         String townName = reader.readLine();

         List<Address> addresses = this.entityManager.createQuery
                 ("SELECT a FROM Address a WHERE a.town.name = :townName", Address.class)
                 .setParameter("townName", townName)
                 .getResultList();

         this.entityManager.getTransaction().begin();

         for (Address address : addresses) {
             for (Employee employee : address.getEmployees()) {
                 employee.setAddress(null);
             }
             this.entityManager.remove(address);
         }

         Town town = this.entityManager.createQuery(
                 "SELECT t FROM Town t WHERE t.name = :townName", Town.class)
                 .setParameter("townName", townName)
                 .getSingleResult();

         this.entityManager.remove(town);

         if (addresses.size() == 1) {
             System.out.printf("1 address in %s deleted%n", townName);
         } else {
             System.out.printf("%d addresses in %s deleted%n", addresses.size(), townName);
         }

         this.entityManager.getTransaction().commit();
     }


     /**
      * 11.	Find Employees by First Name
      * Write a program that finds all employees whose first name starts with pattern given as an input from the console.
      * Print their first, last name, their job title and salary in the format given in the examples below.*/
    private void findEmployeesByFirstName() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();
        this.entityManager.getTransaction().begin();

        List<Employee> employeeList = this.entityManager.createQuery("SELECT e FROM Employee AS e", Employee.class)
                .getResultList();

        employeeList.stream().filter(e ->
                e.getFirstName().toLowerCase().startsWith(pattern.toLowerCase())
        ).forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        this.entityManager.getTransaction().commit();
    }

    /**
     * 12.	Employees Maximum Salaries
     * Write a program to find the max salary for each department.
     * Filter those which have max salaries not in the range 30000 and 70000.*/
    private void employeeMaximumSalaries(){
        this.entityManager.getTransaction().begin();

        List<Object[]> result = this.entityManager.createQuery("" +
                "SELECT d.name, MAX(e.salary) FROM Department AS d " +
                "INNER JOIN Employee AS e " +
                "ON e.department.id = d.id " +
                "GROUP BY d.name " +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000 " +
                "ORDER BY d.id", Object[].class)
                .getResultList();

        for (Object[] tokens : result) {
            System.out.printf("%s - %.2f%n", tokens[0], (BigDecimal) tokens[1]);
        }

        this.entityManager.getTransaction().commit();
    }

}

