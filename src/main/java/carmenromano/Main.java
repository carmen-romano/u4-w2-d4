package carmenromano;
import carmenromano.entitites.Customer;
import carmenromano.entitites.Order;
import carmenromano.entitites.OrderStatus;
import carmenromano.entitites.Product;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class Main {
		public static void main(String[] args) {
           // Creazione prodotti
			Supplier<Product> productSupplier = () -> {
				Faker faker = new Faker();
				Random random = new Random();
				return new Product(faker.harryPotter().character(),faker.harryPotter().house(), random.nextDouble(0, 1000));
			};
			List<Product> productList = new ArrayList<>();

			for (int i = 0; i < 10; i++) {
				productList.add(productSupplier.get());
			}

            // Creazione clienti
			Supplier<Customer> customer = () -> {
				Faker faker = new Faker();
				Random random = new Random();
				return new Customer(faker.harryPotter().character(), random.nextInt(0, 10));
			};
			List<Customer> customerList = new ArrayList<>();

			for (int i = 0; i < 10; i++) {
				customerList.add(customer.get());
			}
			// Creazione ordini

			Order order1 = new Order(OrderStatus.PROCESSING, LocalDate.now(), LocalDate.now().plusDays(3), Arrays.asList(productList.get(2), productList.get(7)), customerList.get(1));
			Order order3 = new Order(OrderStatus.SHIPPED, LocalDate.parse("2021-03-01"), LocalDate.now().plusDays(2), Arrays.asList(productList.get(2), productList.get(2)), customerList.get(3));
			Order order4 = new Order(OrderStatus.DELIVERED, LocalDate.parse("2021-02-15"), LocalDate.parse("2021-02-20"), Arrays.asList(productList.get(1), productList.get(2)), customerList.get(1));
			List<Order> orderList = Arrays.asList(order1, order3, order4);



			System.out.println("///*****************ESERCIZIO1********************////");
			Map<Customer, List<Order>> orderCustomer = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer()));
			System.out.println("Ordini raggruppati per cliente:");
			orderCustomer.forEach((cliente, ordine) -> System.out.println("Cliente: " + cliente + ",lista ordini: " + ordine));



			System.out.println("///*****************ESERCIZIO2********************////");
			Map<Customer, Double> totaleArticoli = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer(), Collectors.summingDouble(order
					-> order.getProducts().stream().mapToDouble(product -> product.getPrice()).sum())));
			System.out.println("totale per ogni ordine: ");
			totaleArticoli.forEach((cliente, totaleOrdini) -> System.out.println("Cliente: " + cliente + ",totale ordini: " + totaleOrdini));



			System.out.println("///*****************ESERCIZIO3********************////");
			OptionalDouble prodottoPiuCostoso = productList.stream().mapToDouble(Product -> Product.getPrice()).max();
			if (prodottoPiuCostoso.isPresent()) {
				System.out.println("Il prodotto costa: " + prodottoPiuCostoso.getAsDouble() );
			} else {
				System.out.println("Non ci sono prodotti");
			}
			List<Product> usersSortedByAge = productList.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(3).toList();
			usersSortedByAge.forEach(product -> System.out.println("I 3 prodotti piu costosi: "+product));



			System.out.println("///*****************ESERCIZIO4********************////");
			OptionalDouble mediaPrezzi = orderList.stream().flatMapToDouble(order -> order.getProducts().stream().mapToDouble(Product -> Product.getPrice())).average();
			if (mediaPrezzi.isPresent()) {
				System.out.println("Il costo medio dei prodotto presenti negli ordini è : " + mediaPrezzi.getAsDouble());
			} else {
				System.out.println("Non ci sono prodotti");
			}



			System.out.println("///*****************ESERCIZIO5********************////");
			Map<String, Double> sommaPrezziPerCategoria = productList.stream().collect(Collectors.groupingBy(user -> user.getCategory(), Collectors.summingDouble(user -> user.getPrice())));

			sommaPrezziPerCategoria.forEach((categoria, somma) -> System.out.println("Categoria: " + categoria + ", somma: " + somma));


		}

	}