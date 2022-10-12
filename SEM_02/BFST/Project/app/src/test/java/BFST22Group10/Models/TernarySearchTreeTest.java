package BFST22Group10.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TernarySearchTreeTest {
    TernarySearchTree ternarySearchTree;
    int totalAddresses = 30;
    Address storkevang;
    Address kratbjerg;
    Address home;

    @BeforeEach
    void setUp() {
        ternarySearchTree = new TernarySearchTree();
        for (int i = 1; i <= totalAddresses; i++) {
            Address address = new Address();
            address.setHousenumber("" + i);
            address.setStreet("Søhuse");
            ternarySearchTree.insert(address);
        }

        Address address = new Address();
        address.setHousenumber("3");
        address.setStreet("Søvej");
        ternarySearchTree.insert(address);

        address = new Address();
        address.setHousenumber("3");
        address.setStreet("Søhaven");
        ternarySearchTree.insert(address);

        address = new Address();
        address.setHousenumber("12");
        address.setStreet("Tordenskjoldsgade");
        ternarySearchTree.insert(address);

        address = new Address();
        address.setHousenumber("13");
        address.setStreet("Søanemonevej");
        ternarySearchTree.insert(address);

        home = new Address();
        home.setHousenumber("3");
        home.setStreet("Hjemmet");
        ternarySearchTree.insert(home);

        storkevang = new Address();
        storkevang.setHousenumber("1");
        storkevang.setStreet("Storkevang");
        ternarySearchTree.insert(storkevang);

        kratbjerg = new Address();
        kratbjerg.setHousenumber("5");
        kratbjerg.setStreet("Kratbjerg");
        ternarySearchTree.insert(kratbjerg);

        ternarySearchTree.reduceTree();
    }

    @Test
    void searchReturnsCorrectAmount() {
        Set<Address> returnSet = ternarySearchTree.prefixSearch("Søh");
        Set<Address> wrongCaseReturnSet = ternarySearchTree.prefixSearch("sØh");

        assertEquals(returnSet.size(), 10, "The search should return a limited number of roads to increase speed");
        assertEquals(wrongCaseReturnSet.size(), returnSet.size(), "An address search should not rely on correct capitalization of the search string");
    }

    @Test
    void emptySearchReturnsNull() {
        assertNull(ternarySearchTree.getAddress(""), "Searching for an empty address string should return null");
        assertEquals(new HashSet<Address>(), ternarySearchTree.prefixSearch(""), "Empty search should return empty set of results");
    }

    @Test
    void getAddress() {
        Address searchedAddress = ternarySearchTree.getAddress(Address.valueOf("Storkevang 1").toString());
        assertEquals(storkevang, searchedAddress, "Searching for an address with tostring from an address object should return the address object located in the ternary tree");
    }

    @Test
    void getInvalidAddress() {
        Address address = ternarySearchTree.getAddress(Address.valueOf("White House, Washington").toString());
        assertNull(address, "An address not in the ternary search tree should return a null object");
    }

    @Test
    void searchFullAddress() {
        Set<Address> allAddresses = ternarySearchTree.prefixSearch(storkevang.toString());
        assertTrue(allAddresses.contains(storkevang), "Searching for an address that exists should include the correct address in the set");
        assertEquals(1, allAddresses.size(), "Searching for a complete address string should only return the address it matches");
    }

    @Test
    void searchNullAddress() {
        assertNull(ternarySearchTree.prefixSearch(null), "Searching for a null address should return null");
    }

    @Test
    void insertWordAfterReduction() {
        Address POI = Address.valueOf("Hjem");
        ternarySearchTree.insert(POI);
        assertEquals(POI, ternarySearchTree.getAddress("Hjem"), "It should be possible to add a point of interest after the tree has been reduced");
        assertEquals(home, ternarySearchTree.getAddress("Hjemmet"), "It should be possible to add a point of interest after the tree has been reduced");
    }
}