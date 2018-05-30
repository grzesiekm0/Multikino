/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

/**
 * Ogólny interfejs modelu danych może implementować plik tekstowy jak i bazę danych albo inne źródło
 * Interfejs zawiera podstawowe operracje CRUD jak i zestaw metod do otwarcia i zamknięcia połączenia
 * @author 
 */
public interface GenericModel {
    
    
    /**
     *  Otwiera połączenie ze źródłem to może być otwarcie i odczytanie zawartości plików albo otwarcie połączenia z BD
     * @return 
     */
    public boolean openConnection(String sSource);
    
    /**
     *  Zamyka połączenie ze źródłem danych
     * @return 
     */
    public boolean closeConnection();
    
     /**
     * aktualizuje dane w bazie danych/pliku
     * @param d - parametr określający dane (to mogą byc nowi widzowie, nowy film, nowy seans itp)
     * @return 
     */
    public boolean updateData(Dane d);
    
    /**
     * dodaje dane do bazy danych/pliku
     * @param d - parametr określający dane (to mogą byc nowi widzowie, nowy film, nowy seans itp)
     * @return 
     */
    public boolean insertData(Dane d);
    
    /**
     * usuwa dane z bazy danych/pliku
     * @param d - parametr określający dane (to mogą byc nowi widzowie, nowy film, nowy seans itp)
     * @return 
     */
    public boolean deleteData(Dane d);
    
}
