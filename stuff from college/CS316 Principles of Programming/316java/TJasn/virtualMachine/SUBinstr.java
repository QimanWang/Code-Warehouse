package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.ESP;
import static TJasn.virtualMachine.CodeInterpreter.EXPRSTACK;

public class SUBinstr extends ZeroOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
    /* ???????? *///done
    EXPRSTACK[(--ESP - 1)] -=  EXPRSTACK[ESP];
  }

  public SUBinstr ()
  {
    super("SUB");
  }
}

