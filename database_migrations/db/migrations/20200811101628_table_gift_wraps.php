<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableGiftWraps extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $giftWraps = $this->table('shop_giftwrap', ['signed' => false]);
        $giftWraps->addColumn('shop_id', 'biginteger', ['signed' => false])
        ->addColumn('image_path', 'string', ['limit' => 52])
        ->addColumn('active', 'boolean')
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();


        $tableProducts = $this->table('products');
        $tableProducts->addColumn('gift_wrap_cost', 'decimal', ['precision' => 10,'scale'=>2])->update();
      
    }
}
