package company.service;

import company.model.Ipo;
import company.repository.IpoRepository;
import org.springframework.stereotype.Service;

@Service
public class IpoServiceImpl implements IpoService{
    private IpoRepository ipoRepository;


    public IpoServiceImpl(IpoRepository ipoRepository){
        this.ipoRepository = ipoRepository;
    }

    @Override
    public Iterable<Ipo> getIpo() {
        return ipoRepository.findAll();
    }

    @Override
    public Ipo addIpo(Ipo ipo) {
        return ipoRepository.save(ipo);

    }

    @Override
    public Iterable<Ipo> findByCompanyName(String companyName) {
        return ipoRepository.findByCompanyName(companyName);
    }

    @Override
    public boolean ifExistsById(String id){
        return ipoRepository.existsById(id);
    }

    public boolean ifExistsByName(String name){return ipoRepository.existsByCompanyName(name);}
}
