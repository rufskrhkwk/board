package pegsystem.jdbc;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.naming.Context;
import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.XADataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class EncryptDataSourceFactory2 extends DataSourceFactory {

	private static final Logger logger = LoggerFactory.getLogger(EncryptDataSourceFactory2.class);
	private Encryptor encryptor = null;

	
	
	
	public EncryptDataSourceFactory2() {
		try {
			encryptor = new Encryptor(); // If you've used your own secret key, pass it in...
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException e) {
			logger.info("Error instantiating decryption class." + e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public DataSource createDataSource(Properties properties, Context context, boolean XA)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, SQLException, NoSuchAlgorithmException, NoSuchPaddingException {
		// Here we decrypt our password.
		PoolConfiguration poolProperties = EncryptDataSourceFactory2.parsePoolProperties(properties);
		poolProperties.setUsername(encryptor.decrypt(poolProperties.getUsername()));
		poolProperties.setPassword(encryptor.decrypt(poolProperties.getPassword()));
		
		// The rest of the code is copied from Tomcat's DataSourceFactory.
		if(poolProperties.getDataSourceJNDI() != null && poolProperties.getDataSource() == null) {
			performJNDILookup(context, poolProperties);
		}
		
		org.apache.tomcat.jdbc.pool.DataSource dataSource = XA ? new XADataSource(poolProperties) : new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
		dataSource.createPool();
		return dataSource;
	}

	
	
	
	
	
}
