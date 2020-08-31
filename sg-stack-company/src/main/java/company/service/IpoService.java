package company.service;

import company.model.Ipo;

public interface IpoService {

    public Iterable<Ipo> getIpo();
    public Ipo addIpo(Ipo ipo);
    public Iterable<Ipo> findByCompanyName(String companyName);
    public boolean ifExistsById(String id);
    public boolean ifExistsByName(String name);
}
