public class BouncySponges{

        @SubscribeEvent
        public void makeJumpHigher(LivingJumpEvent event){
            if(!(event.getEntity() instanceof EntityPlayer)){
                return;
                //exit if it's not a player
            }

            BlockPos pos = new BlockPos(
                    event.getEntity().posX,
                    event.getEntity().posY -1 ,
                    event.getEntity().posZ);

            //this part checks if the block is a sponge block
            if(event.getEntity().worldObj.getBlockState(pos).getBlock() != Blocks.SPONGE ){
                return;
            }

            //here i know it's a player and a sponge block
            event.getEntity().motionY *= 10;


    }

}