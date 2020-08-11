<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableShopManagers extends AbstractMigration
{

    public function change(): void
    {
        $shopManagers = $this->table('shop_managers', ['signed' => false]);
        $shopManagers->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('manager_id', 'biginteger', ['signed' => false])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->addForeignKey('manager_id', 'users', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
