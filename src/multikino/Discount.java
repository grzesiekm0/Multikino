/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multikino;

/**
 *
 * Enumerator zniżek dla biletów wstepu.
 */
public enum Discount {
    NONE(0),
    KARTA_DUZEJ_RODZINY(1),
    EMERYT(2),
    ULGA_SZKOLNA(3),
    ULGA_STUDENCKA(4),
    NIEPELNOSPRAWNY(5),
    WETERAN(6),
    HDK(7),
    CIEZARNE(8);
    
    int disc;
    
    Discount(int d) {
        disc = d;
    }
    @Override    
    public String toString() {
        String sRet = "";
        switch(disc) {
            case(0):
                sRet = "bilet normalny";
                break;
            case(1):
                sRet = "Karta Dużej Rodziny";
                break;
            case(2):
                sRet = "zniżka dla seniora";
                break;
            case(3):
                sRet = "ulga szkolna";
                break;
            case(4):
                sRet = "ulga studencka";
                break;
            case(5):
                sRet = "ulga z tytułu niepełnosprawności";
                break;
            case(6):
                sRet = "ulga kombatancka";
                break;
            case(7):
                sRet = "ulga za honorowe krwiodawstwo";
                break;
            case(8):
                sRet = "ulga dla kobiet w ciąży";
                break;

            default:
                sRet = "bilet normalny";
                break;
        }
        return sRet;
    }
}
