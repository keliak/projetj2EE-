package metier.DAO.IMetier;

import java.util.List;
import metier.entity.Type;

public interface ITypeDAO {
	public List<Type> getType();
	public Type getTypeById(String id);
}
