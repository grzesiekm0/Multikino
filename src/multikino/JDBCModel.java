/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

/**
 *
 * @author 
 */
public class JDBCModel implements GenericModel{

    @Override
    public boolean openConnection(String sSource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateData(Dane d) {
        boolean bRet = false;
        if(d instanceof Film) {
            
        }
        else if(d instanceof Aktor) {
            
        }
        else if(d instanceof Widz) {
            
        }
//        else if(d instanceof Seans) {
//            
//        }
//        else 
        return bRet;
    }

    @Override
    public boolean insertData(Dane d) {

        boolean bRet = false;
        if(d instanceof Film) {
            
        }
        else if(d instanceof Aktor) {
            
        }
        else if(d instanceof Widz) {
            
        }
//        else if(d instanceof Seans) {
//            
//        }
//        else 
        return bRet;
    }

    @Override
    public boolean deleteData(Dane d) {
        boolean bRet = false;
        if(d instanceof Film) {
            
        }
        else if(d instanceof Aktor) {
            
        }
        else if(d instanceof Widz) {
            
        }
//        else if(d instanceof Seans) {
//            
//        }
//        else 

        return bRet;
    }
    
}
