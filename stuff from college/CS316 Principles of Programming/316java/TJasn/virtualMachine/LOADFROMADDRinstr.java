package TJasn.virtualMachine;

import TJasn.TJ;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class LOADFROMADDRinstr extends ZeroOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
       /* ???????? *///done
    EXPRSTACK[ESP-1] = TJ.data[EXPRSTACK[ESP-1] - POINTERTAG];

  }

  public LOADFROMADDRinstr ()
  {
    super("LOADFROMADDR");
  }
}

