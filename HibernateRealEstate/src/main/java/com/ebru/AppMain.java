package com.ebru;

import com.ebru.dao.AgentDAO;
import com.ebru.dao.BuyerDAO;
import com.ebru.dao.PropertyDAO;
import com.ebru.dao.SellerDAO;
import com.ebru.model.Agent;
import com.ebru.model.Buyer;
import com.ebru.model.Property;
import com.ebru.model.Seller;

import java.math.BigDecimal;
import java.util.List;

public class AppMain {
    public static void main(String[] args) {

        AgentDAO agentDAO = new AgentDAO();
        PropertyDAO propertyDAO = new PropertyDAO();
        SellerDAO sellerDAO = new SellerDAO();
        BuyerDAO buyerDAO = new BuyerDAO();

        Agent agent = new Agent();
        agent.setAgentName("Freedom RealEstate");
        agent.setPhone("123456789");
        agent.setEmail("fredomre123@abc.com");
        agent.setOfficeAddress("USA NY");
        agentDAO.saveOrUpdate(agent);

        Agent agent2 = new Agent();
        agent2.setAgentName("Pascal RealEstate");
        agent2.setPhone("12348888");
        agent2.setEmail("pascalre456@abc.com");
        agent2.setOfficeAddress("Italy");
        agentDAO.saveOrUpdate(agent2);


        Buyer buyer = new Buyer();
        buyer.setFirstName("Jeniffer");
        buyer.setLastName("Frank");
        buyer.setEmail("abc@aaa.com");
        buyer.setPhone("3333");
        buyerDAO.saveOrUpdate(buyer);

        Buyer buyer2 = new Buyer();
        buyer2.setFirstName("George");
        buyer2.setLastName("Igor");
        buyer2.setEmail("yyyy@aaa.com");
        buyer2.setPhone("444");
        buyerDAO.saveOrUpdate(buyer2);



        Seller seller = new Seller();
        seller.setFirstName("Tom");
        seller.setLastName("Jerry");
        seller.setEmail("dddd@aaa.com");
        seller.setPhone("8888");
        sellerDAO.saveOrUpdate(seller);

        Seller seller2 = new Seller();
        seller2.setFirstName("Victor");
        seller2.setLastName("Valery");
        seller2.setEmail("tttt@aaa.com");
        seller2.setPhone("233252");
        sellerDAO.saveOrUpdate(seller2);


        Property property = new Property();
        property.setPropertyName("Personal Property");
        property.setTitle("Dublex");
        property.setDescription("10 Rooms 3 Bathrooms");
        property.setType("For Rent");
        property.setBedroom(10);
        property.setBathroom(3);
        property.setPrice(BigDecimal.valueOf(10_000_000.75));
        property.setArea(450.66f);
        property.setLocation("California");
        // relation
        property.setAgent(agent);
        property.setSeller(seller);
        property.getBuyers().add(buyer);
        property.getBuyers().add(buyer2);

        // insert create
        propertyDAO.saveOrUpdate(property);

        property.setType("SOLD");

        //update
        propertyDAO.saveOrUpdate(property);

        Property property2 = new Property();
        property2.setPropertyName("ABC Hotel");
        property2.setTitle("Hotel");
        property2.setDescription("central, near the sea");
        property2.setType("For Sale");
        property2.setBedroom(20);
        property2.setBathroom(15);
        property2.setPrice(BigDecimal.valueOf(200_000_000.75));
        property2.setArea(650.88f);
        property2.setLocation("Spain Valencia");
        // ilişki
        property2.setAgent(agent2);
        property2.setSeller(seller2);
        property2.getBuyers().add(buyer);
        property2.getBuyers().add(buyer2);

        propertyDAO.saveOrUpdate(property2);

        System.out.println("Agent");
        List<Agent> agentList = agentDAO.getFindAll();
        for (Agent a: agentList) {
            System.out.println(a.getId() + " " + a.getAgentName() + " ");
        }
        System.out.println("------------------");

        System.out.println("Seller");
        List<Seller> sellerList = sellerDAO.getFindAll();
        for (Seller s: sellerList ) {
            System.out.println(s.getId() + " " + s.getFirstName()+ " " + s.getLastName());
        }
        System.out.println("------------------");

        System.out.println("Buyer");
        List<Buyer> buyerList = buyerDAO.getFindAll();
        for (Buyer b: buyerList) {
            System.out.println(b.getId()+ " " + b.getFirstName() + " " + b.getLastName());
        }
        System.out.println("------------------");

        System.out.println("Buyer 1");
        Buyer buyer1 = buyerDAO.getFindById(1L);
        System.out.println(buyer1.getId()+ " " + buyer1.getFirstName() + " " + buyer1.getLastName());
        System.out.println("------------------");
    }
}