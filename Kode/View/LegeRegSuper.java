/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Model.Lege;
import javax.swing.JFrame;

/**
 *
 * @author Hallbjørn
 */
public abstract class LegeRegSuper  extends JFrame{

    public LegeRegSuper(String tittel)
    {
        super(tittel);
    }
    public abstract boolean registrerLege(String fornavn, String etternavn, String ep, 
                String gadresse, int pNr, String psted, String as, char[] pass);
    
    public abstract Lege login(String epost, char[] passord);



}
