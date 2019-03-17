package TJasn.virtualMachine;

import TJasn.TJ;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class PASSPARAMinstr extends ZeroOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
      /* ???????? *///done
    TJ.data[ASP++ - POINTERTAG] = EXPRSTACK[--ESP];
  }

  public PASSPARAMinstr ()
  {
    super("PASSPARAM");
  }
}

