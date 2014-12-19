package cz.raptors.dianotes.dao;

import cz.raptors.dianotes.dao.exception.AddressNotFoundException;
import cz.raptors.dianotes.entities.Address;
import cz.raptors.dianotes.entities.User;

import java.util.List;

/**
 * Created by vamvda1 on 1.4.14.
 */
public interface AddressDao extends GenericDao<Address, AddressNotFoundException> {

    /**
     * Returns all addresses for user.
     * @param user
     * @return
     */
    public List<Address> getAddressesForUser(User user);


}
