package TJasn.virtualMachine;

import TJasn.TJ;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class SAVETOADDRinstr extends ZeroOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
     /* ???????? *///done
    TJ.data[EXPRSTACK[--ESP-1]-POINTERTAG] = EXPRSTACK[ESP--];
  }

  public SAVETOADDRinstr ()
  {
    super("SAVETOADDR");
  }
}

