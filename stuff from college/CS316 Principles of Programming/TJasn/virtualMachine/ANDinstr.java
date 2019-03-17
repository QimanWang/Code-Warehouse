package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class ANDinstr extends ZeroOperandInstruction {

  void execute ()
  {
    /* ???????? *///done
    EXPRSTACK[--ESP-1] &= EXPRSTACK[ESP];
  }

  public ANDinstr ()
  {
    super("AND");
  }
}
