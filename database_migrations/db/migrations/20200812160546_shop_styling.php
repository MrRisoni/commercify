<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class ShopStyling extends AbstractMigration
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
        $shopStyling = $this->table('shop_styling', ['signed' => false]);
        $shopStyling->addColumn('shop_id', 'biginteger', ['signed' => false])
        ->addColumn('style', 'text' )
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])->create();
        
    }
}
