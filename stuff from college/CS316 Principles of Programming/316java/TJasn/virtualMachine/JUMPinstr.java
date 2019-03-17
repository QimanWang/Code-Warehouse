package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class JUMPinstr extends OneOperandInstruction {

  void execute ()
  {
    /* ???????? *///done
    PC = operand;
  }

  public JUMPinstr (int address)
  {
    super(address, "JUMP");
  }
}

