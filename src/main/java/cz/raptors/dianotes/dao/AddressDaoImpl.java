package cz.raptors.dianotes.dao;

import cz.raptors.dianotes.dao.exception.AddressNotFoundException;
import cz.raptors.dianotes.entities.Address;
import cz.raptors.dianotes.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vamvda1 on 1.4.14.
 */
@Repository
public class AddressDaoImpl extends GenericDaoJpa<Address, AddressNotFoundException> implements AddressDao {

    @Override
    public List<Address> getAddressesForUser(User user) {
        return null;
    }

    @Override
    protected Class<Address> getEntityClass() {
        return Address.class;
    }

    @Override
    protected AddressNotFoundException getException(String message) {
        return new AddressNotFoundException(message);
    }
}
